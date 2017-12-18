/**
 * Copyright (C) 2017 Talend Inc. - www.talend.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.talend.tools.blackduck;

import static org.apache.maven.plugins.annotations.LifecyclePhase.VERIFY;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.ToIntFunction;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Server;
import org.slf4j.LoggerFactory;

import com.blackducksoftware.integration.exception.EncryptionException;
import com.blackducksoftware.integration.exception.IntegrationException;
import com.blackducksoftware.integration.hub.CredentialsBuilder;
import com.blackducksoftware.integration.hub.api.report.AggregateBomViewEntry;
import com.blackducksoftware.integration.hub.api.report.ReportCategoriesEnum;
import com.blackducksoftware.integration.hub.api.report.ReportRequestService;
import com.blackducksoftware.integration.hub.api.report.VersionReport;
import com.blackducksoftware.integration.hub.dataservice.project.ProjectDataService;
import com.blackducksoftware.integration.hub.dataservice.project.ProjectVersionWrapper;
import com.blackducksoftware.integration.hub.global.HubServerConfig;
import com.blackducksoftware.integration.hub.model.enumeration.ReportFormatEnum;
import com.blackducksoftware.integration.hub.proxy.ProxyInfoBuilder;
import com.blackducksoftware.integration.hub.rest.CredentialsRestConnection;
import com.blackducksoftware.integration.hub.service.HubServicesFactory;
import com.blackducksoftware.integration.log.Slf4jIntLogger;

@Mojo(name = "validate", defaultPhase = VERIFY, threadSafe = true)
public class BlackduckValidateMojo extends BlackduckBase {

    @Parameter(property = "hub-detect.alwaysTrustServerCertificate", defaultValue = "false")
    private boolean alwaysTrustServerCertificate;

    @Parameter(property = "hub-detect.acceptedOperationalHigh", defaultValue = "0")
    private int acceptedOperationalHigh;

    @Parameter(property = "hub-detect.acceptedLicenseRiskHigh", defaultValue = "0")
    private int acceptedLicenseRiskHigh;

    @Parameter(property = "hub-detect.acceptedVulerabilityRiskHigh", defaultValue = "0")
    private int acceptedVulerabilityRiskHigh;

    @Override
    public void doExecute(final MavenProject mvnProject, final Server credentials)
            throws MojoExecutionException, MojoFailureException {
        final CredentialsBuilder credentialsBuilder = new CredentialsBuilder();
        credentialsBuilder.setUsername(credentials.getUsername());
        credentialsBuilder.setPassword(credentials.getPassword());
        alwaysTrustServerCertificate = true;
        final CredentialsRestConnection restConnection;
        try {
            restConnection = new HubServerConfig(new URL(blackduckUrl), 60, credentialsBuilder.buildObject(),
                    new ProxyInfoBuilder().buildObject(), alwaysTrustServerCertificate)
                            .createCredentialsRestConnection(new Slf4jIntLogger(LoggerFactory.getLogger("client")));
        } catch (final MalformedURLException | EncryptionException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
        final HubServicesFactory hsf = new HubServicesFactory(restConnection);
        final ReportRequestService reportService = hsf.createReportRequestService(60000);
        final ProjectDataService projectService = hsf.createProjectDataService();
        ProjectVersionWrapper project;
        try {
            project = projectService.getProjectVersion(blackduckName, mvnProject.getVersion());
        } catch (final IntegrationException e) {
            if (!mvnProject.getVersion().endsWith("-SNAPSHOT")) { // use the closest version, ie the snapshot
                try {
                    project = projectService.getProjectVersion(blackduckName, mvnProject.getVersion() + "-SNAPSHOT");
                } catch (final IntegrationException e1) {
                    try {
                        project = projectService.getProjectVersionAndCreateIfNeeded(blackduckName, mvnProject.getVersion());
                    } catch (final IntegrationException e2) {
                        throw new MojoExecutionException(e.getMessage(), e);
                    }
                }
            } else {
                try {
                    project = projectService.getProjectVersionAndCreateIfNeeded(blackduckName, mvnProject.getVersion());
                } catch (final IntegrationException e2) {
                    throw new MojoExecutionException(e.getMessage(), e);
                }
            }
        }
        final VersionReport report;
        try {
            report = reportService.generateHubReport(project.getProjectVersionView(), ReportFormatEnum.JSON,
                    new ReportCategoriesEnum[] { ReportCategoriesEnum.COMPONENTS });
        } catch (IntegrationException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }

        validate(report, "operational", it -> it.getRiskProfile().getCategories().getOPERATIONAL().getHIGH(),
                acceptedOperationalHigh);
        validate(report, "license", it -> it.getLicenseRisk().getHIGH(), acceptedLicenseRiskHigh);
        validate(report, "vulnerability", it -> it.getVulnerabilityRisk().getHIGH(), acceptedVulerabilityRiskHigh);
    }

    private void validate(final VersionReport report, final String what, final ToIntFunction<AggregateBomViewEntry> mapper,
            final int limit) throws MojoFailureException {
        final int count = report.getAggregateBomViewEntries().stream().mapToInt(mapper).sum();
        if (count > limit) {
            final String message = String.format("Found #%d %s high violations, accepted: #%d", count, what, limit);
            getLog().error(message);
            throw new MojoFailureException(message);
        }
    }
}
