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

import static java.util.Optional.ofNullable;
import static org.apache.maven.plugins.annotations.LifecyclePhase.VERIFY;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Server;
import org.apache.maven.settings.crypto.DefaultSettingsDecryptionRequest;
import org.apache.maven.settings.crypto.SettingsDecrypter;
import org.apache.maven.shared.utils.io.FileUtils;
import org.apache.maven.shared.utils.io.IOUtil;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.impl.ArtifactResolver;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.ArtifactRequest;
import org.eclipse.aether.resolution.ArtifactResolutionException;
import org.eclipse.aether.resolution.ArtifactResult;

@Mojo(name = "hub-detect", defaultPhase = VERIFY, threadSafe = true)
public class HubDetectMojo extends AbstractMojo {

    @Parameter(property = "hub-detect.hubDetectCache", defaultValue = "${project.build.directory}/blackduck/hub-detect.jar")
    private File hubDetectCache;

    @Parameter(property = "hub-detect.artifactoryBase", defaultValue = "https://test-repo.blackducksoftware.com/artifactory")
    private String artifactoryBase;

    @Parameter(property = "hub-detect.latestVersionUrl", defaultValue = "%s/api/search/latestVersion?g=%s&a=%s&repos=%s")
    private String latestVersionUrl;

    @Parameter(property = "hub-detect.executableGav", defaultValue = "com.blackducksoftware.integration:hub-detect:latest")
    private String executableGav;

    @Parameter(property = "hub-detect.executableGav", defaultValue = "bds-integrations-release")
    private String artifactRepositoryName;

    @Parameter(property = "hub-detect.serverId", defaultValue = "blackduck")
    private String serverId;

    @Parameter(property = "hub-detect.blackduckUrl")
    private String blackduckUrl;

    @Parameter(property = "hub-detect.blackduckUrl", defaultValue = "ALL")
    private String logLevel;

    @Parameter(property = "hub-detect.blackduckName")
    private String blackduckName;

    @Parameter(property = "hub-detect.validateExitCode")
    private String validateExitCode;

    @Parameter(defaultValue = "${session}", readonly = true)
    private MavenSession session;

    @Component
    private SettingsDecrypter settingsDecrypter;

    @Component
    private ArtifactResolver resolver;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (session.getSettings().isOffline()) {
            getLog().info("Execution is offline, blackduck hub-detect plugin is skipped");
            return;
        }

        if (blackduckUrl == null) {
            getLog().error("No url specified, please set blackduckUrl");
            return;
        }
        if (blackduckName == null) {
            getLog().error("No name specified, please set blackduckName");
            return;
        }

        MavenProject rootProject = session.getCurrentProject();
        while (rootProject.getParent() != null) {
            rootProject = rootProject.getParent();
        }
        final File root = rootProject.getBasedir();

        final Optional<Server> serverOpt = session.getSettings().getServers().stream().filter(s -> serverId.equals(s.getId()))
                .findFirst();
        if (!serverOpt.isPresent()) {
            getLog().warn("No server '" + serverId + "', skipping blackduck execution");
            return;
        }

        Server server = serverOpt.get();
        if ("skip".equals(server.getPassword())) {
            getLog().warn("server '" + serverId + "' was configured to be skipped");
            return;
        }
        server = ofNullable(settingsDecrypter.decrypt(new DefaultSettingsDecryptionRequest(server)).getServer()).orElse(server);

        final File jar;
        final String[] gav = executableGav.split(":");
        if (!hubDetectCache.exists()) {
            final String hubDetectVersion;
            if (!"latest".equalsIgnoreCase(gav[2])) {
                hubDetectVersion = gav[2];
            } else {
                try {
                    final URL versionUrl = new URL(
                            String.format(latestVersionUrl, artifactoryBase, gav[0], gav[1], artifactRepositoryName));
                    try (final InputStream stream = versionUrl.openStream()) {
                        hubDetectVersion = IOUtil.toString(stream);
                    }
                } catch (final IOException e) {
                    throw new IllegalArgumentException(e); // unlikely
                }
            }

            hubDetectCache.getParentFile().mkdirs();

            final List<RemoteRepository> repositories = new ArrayList<>(rootProject.getRemoteProjectRepositories().size() + 1);
            repositories.add(new RemoteRepository.Builder("blackduck_" + getClass().getName(), "default",
                    artifactoryBase + '/' + artifactRepositoryName).build());
            repositories.addAll(rootProject.getRemoteProjectRepositories());
            try {
                final ArtifactResult artifactResult = resolver.resolveArtifact(session.getRepositorySession(),
                        new ArtifactRequest(new DefaultArtifact(gav[0], gav[1], "jar", hubDetectVersion), repositories, null));
                if (artifactResult.isMissing()) {
                    throw new IllegalStateException("Didn't find '" + executableGav + "'");
                }
                jar = artifactResult.getArtifact().getFile();
            } catch (final ArtifactResolutionException e) {
                throw new IllegalStateException("Didn't find '" + executableGav + "'", e);
            }
            try {
                FileUtils.copyFile(jar, hubDetectCache);
            } catch (final IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            jar = hubDetectCache;
        }

        final File java = new File(System.getProperty("java.home"), "bin/java");
        final ProcessBuilder processBuilder = new ProcessBuilder().inheritIO().command(java.getAbsolutePath(), "-jar",
                hubDetectCache.getAbsolutePath());
        final Map<String, String> environment = processBuilder.environment();
        environment.put("SPRING_APPLICATION_JSON",
                "{\n\"blackduck.hub.url\": \"" + blackduckUrl + "\",\n" + "\"blackduck.hub.username\": \"" + server.getUsername()
                        + "\",\n" + "\"blackduck.hub.password\": \"" + server.getPassword() + "\",\n"
                        + "\"logging.level.com.blackducksoftware.integration\": \"" + logLevel + "\",\n"
                        + "\"detect.project.name\": \"" + blackduckName + "\",\n" + "\"detect.source.path\": \""
                        + root.getAbsolutePath() + "\"\n}");
        getLog().info("Launching: " + processBuilder.command());

        final int exitStatus;
        try {
            exitStatus = processBuilder.start().waitFor();
        } catch (final InterruptedException e) {
            Thread.interrupted();
            getLog().error(e);
            throw new IllegalStateException(e);
        } catch (final IOException e) {
            getLog().error(e);
            throw new IllegalStateException(e);
        }

        getLog().info("Output: " + exitStatus);

        int expectedExitCode;
        try {
            expectedExitCode = Integer.parseInt(validateExitCode);
        } catch (final NumberFormatException nfe) {
            if (Boolean.parseBoolean(validateExitCode)) {
                expectedExitCode = 0;
            } else {
                return;
            }
        }
        if (exitStatus != expectedExitCode) {
            throw new IllegalStateException("Invalid exit status: " + exitStatus);
        }
    }
}
