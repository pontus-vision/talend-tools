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

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.apache.maven.plugins.annotations.LifecyclePhase.VERIFY;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Server;
import org.apache.maven.shared.utils.io.FileUtils;
import org.apache.maven.shared.utils.io.IOUtil;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.impl.ArtifactResolver;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.ArtifactRequest;
import org.eclipse.aether.resolution.ArtifactResolutionException;
import org.eclipse.aether.resolution.ArtifactResult;

import com.google.gson.GsonBuilder;

/**
 * Download if not already cached in maven repository and execute blackduck hub-detect.
 */
@Mojo(name = "hub-detect", defaultPhase = VERIFY, threadSafe = true)
public class HubDetectMojo extends BlackduckBase {

    /**
     * Where the jar will be put for the execution.
     */
    @Parameter(property = "hub-detect.hubDetectCache", defaultValue = "${project.build.directory}/blackduck/hub-detect.jar")
    private File hubDetectCache;

    /**
     * In which (artifactory) repository the jar can be found.
     */
    @Parameter(property = "hub-detect.artifactoryBase", defaultValue = "https://test-repo.blackducksoftware.com/artifactory")
    private String artifactoryBase;

    /**
     * What is the query used to get the last version of hub-detect. Passed variables are the repository base, group, artifact and
     * repo.
     */
    @Parameter(property = "hub-detect.latestVersionUrl", defaultValue = "%s/api/search/latestVersion?g=%s&a=%s&repos=%s")
    private String latestVersionUrl;

    /**
     * The jar coordinates. You can use it to fix the version of hub-detect.
     */
    @Parameter(property = "hub-detect.executableGav", defaultValue = "com.blackducksoftware.integration:hub-detect:latest")
    private String executableGav;

    /**
     * The repository to use to download the executable jar.
     */
    @Parameter(property = "hub-detect.artifactRepositoryName", defaultValue = "bds-integrations-release")
    private String artifactRepositoryName;

    /**
     * The log level used for the inspection.
     */
    @Parameter(property = "hub-detect.logLevel", defaultValue = "TRACE") // ALL doesn't work with mvn slf4j default impl
    private String logLevel;

    /**
     * Should the exit code of hub-detect be validated. Can be true or any int. If true, 0 will be tested otherwise
     * the passed value. Any other value will be considered as no validation to execute.
     */
    @Parameter(property = "hub-detect.validateExitCode", defaultValue = "0")
    private String validateExitCode;

    /**
     * The scope used for the detection. It is common to not desire provided.
     */
    @Parameter(property = "hub-detect.scope", defaultValue = "runtime")
    private String scope;

    /**
     * Let you add system properties on hub-detect execution.
     */
    @Parameter
    private Map<String, String> systemVariables;

    /**
     * Let you add environment variables on hub-detect execution.
     */
    @Parameter
    private Map<String, String> environment;

    /**
     * Let you exclude files with an absolute path resolution from relative path.
     * Avoid headache with hub-detect configuration.
     */
    @Parameter
    private Collection<String> exclusions;

    @Component
    private ArtifactResolver resolver;

    @Override
    public void doExecute(final MavenProject rootProject, final Server server)
            throws MojoExecutionException, MojoFailureException {

        if (blackduckName == null) {
            getLog().error("No name specified, please set blackduckName");
            return;
        }

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
                    throw new IllegalStateException(String.format("Didn't find '%s'", executableGav));
                }
                jar = artifactResult.getArtifact().getFile();
            } catch (final ArtifactResolutionException e) {
                throw new IllegalStateException(String.format("Didn't find '%s'", executableGav), e);
            }
            try {
                FileUtils.copyFile(jar, hubDetectCache);
            } catch (final IOException e) {
                throw new IllegalStateException(e);
            }
        }

        final String rootPath = rootProject.getBasedir().getAbsolutePath();
        final File java = new File(System.getProperty("java.home"), "bin/java");
        final List<String> command = new ArrayList<>();
        command.add(java.getAbsolutePath());
        if (systemVariables != null) {
            command.addAll(systemVariables.entrySet().stream()
                    .map(e -> String.format("-D%s=%s", e.getKey(), handlePlaceholders(rootPath, e.getValue())))
                    .collect(toList()));
        }
        final ProcessBuilder processBuilder = new ProcessBuilder().inheritIO().command(command);
        final Map<String, String> environment = processBuilder.environment();
        if (this.environment != null) {
            environment.putAll(this.environment);
        }
        final Map<String, String> config = new HashMap<>();
        // https://blackducksoftware.atlassian.net/wiki/spaces/INTDOCS/pages/68878339/Hub+Detect+Properties
        config.put("blackduck.hub.url", blackduckUrl);
        config.put("blackduck.hub.username", server.getUsername());
        config.put("blackduck.hub.password", server.getPassword());
        config.put("logging.level.com.blackducksoftware.integration", logLevel);
        config.put("detect.project.name", blackduckName);
        config.put("detect.source.path", rootPath);
        config.put("detect.maven.scope", scope);
        if (systemVariables == null || !systemVariables.containsKey("detect.output.path")) {
            config.put("detect.output.path",
                    new File(rootProject.getBuild().getOutputDirectory(), "blackduck").getAbsolutePath());
        }
        if (exclusions != null) {
            config.put("detect.hub.signature.scanner.exclusion.patterns", exclusions.stream().filter(Objects::nonNull).map(e -> {
                final File file = new File(rootProject.getBasedir(), e.trim());
                try {
                    return file.getCanonicalPath();
                } catch (final IOException ex) {
                    return file.getAbsolutePath();
                }
            }).map(p -> p.endsWith("/") ? p : (p + '/')).collect(joining(",")));
        }
        environment.put("SPRING_APPLICATION_JSON", new GsonBuilder().create().toJson(config));
        command.add("-jar");
        command.add(hubDetectCache.getAbsolutePath());
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

        getLog().info(String.format("Output: %d", exitStatus));

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
            throw new IllegalStateException(String.format("Invalid exit status: %d", exitStatus));
        }
    }

    private String handlePlaceholders(final String rootPath, final String value) {
        return value.replace("$rootProject", rootPath);
    }
}
