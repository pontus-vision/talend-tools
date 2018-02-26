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

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Server;
import org.apache.maven.settings.crypto.DefaultSettingsDecryptionRequest;
import org.apache.maven.settings.crypto.SettingsDecrypter;

public abstract class BlackduckBase extends AbstractMojo {

    /**
     * Which server contains the blackduck credentials in your settings.xml.
     */
    @Parameter(property = "hub-detect.serverId", defaultValue = "blackduck")
    protected String serverId;

    /**
     * The blackduck url to use.
     */
    @Parameter(property = "hub-detect.blackduckUrl")
    protected String blackduckUrl;

    /**
     * The application name in blackduck.
     */
    @Parameter(property = "hub-detect.blackduckName", defaultValue = "${project.name}")
    protected String blackduckName;

    /**
     * Enables to skip the execution.
     */
    @Parameter(property = "hub-detect.skip", defaultValue = "false")
    protected boolean skip;

    /**
     * Enables to skip the execution.
     */
    @Parameter(property = "hub-detect.atTheEnd", defaultValue = "true")
    protected boolean atTheEnd;

    @Parameter(defaultValue = "${session}", readonly = true)
    protected MavenSession session;

    @Parameter(defaultValue = "${reactorProjects}", required = true, readonly = true)
    protected List<MavenProject> reactorProjects;

    @Component
    protected SettingsDecrypter settingsDecrypter;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        final AtomicInteger counter = AtomicInteger.class.cast(
                session.getRequest().getData().computeIfAbsent(getClass().getName() + ".counter", k -> new AtomicInteger()));
        if (atTheEnd && !skip && counter.incrementAndGet() != reactorProjects.size()) {
            getLog().debug(
                    String.format("Not yet at the last project, will only run when reached to not do it multiple times %d/%d",
                            counter.get(), reactorProjects.size()));
            return;
        }
        if (skip) {
            getLog().info("Execution is skipped");
            return;
        }

        MavenProject rootProject = session.getCurrentProject();
        while (rootProject.getParent() != null) {
            rootProject = rootProject.getParent();
        }

        if (session.getSettings().isOffline()) {
            getLog().info("Execution is offline, blackduck hub-detect plugin is skipped");
            return;
        }

        if (blackduckUrl == null) {
            getLog().error("No url specified, please set blackduckUrl");
            return;
        }

        final Optional<Server> serverOpt = session.getSettings().getServers().stream().filter(s -> serverId.equals(s.getId()))
                .findFirst();
        if (!serverOpt.isPresent()) {
            getLog().warn(String.format("No server '%s', skipping blackduck execution", serverId));
            return;
        }

        final Server tmpServer = serverOpt.get();
        final Server server = ofNullable(settingsDecrypter.decrypt(new DefaultSettingsDecryptionRequest(tmpServer)).getServer())
                .orElse(tmpServer);
        if ("skip".equals(server.getPassword())) {
            getLog().warn(String.format("server '%s' was configured to be skipped", serverId));
            return;
        }

        doExecute(rootProject, server);
    }

    protected abstract void doExecute(MavenProject rootProject, Server server)
            throws MojoExecutionException, MojoFailureException;
}
