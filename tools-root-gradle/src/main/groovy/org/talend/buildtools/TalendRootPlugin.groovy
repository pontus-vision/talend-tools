package org.talend.buildtools

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.javadoc.Javadoc

class TalendRootPlugin implements Plugin<Project> {

    Project proj;

    Closure setupJavadocJarTask() {
        Closure cl = {
            javadoc {
                classpath = configurations.compile
            }
            tasks.create(name: 'javadocJar', type: Jar) {
                classifier = 'javadoc'
                from javadoc.destinationDir
            }
            artifacts {
                archives javadocJar
            }
            javadocJar.dependsOn javadoc
        }
        cl.delegate = proj
        return cl
    }

    void apply(Project project) {
        proj = project
        proj.extensions.create("talend", TalendRootPluginExtension)

        proj.configure(project) {
            apply plugin: 'java'
            //apply plugin: 'org.dm.bundle'

            println "talend-root configure"
            setupJavadocJarTask().call();

        }
    }
}

class TalendRootPluginExtension {

}
