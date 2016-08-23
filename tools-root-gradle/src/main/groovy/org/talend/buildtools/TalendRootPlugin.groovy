package org.talend.buildtools

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.javadoc.Javadoc

class TalendRootPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.extensions.create("talend", TalendRootPluginExtension)

        project.configure(project) {
            apply plugin: 'java'

            javadoc {
                //source = sourceSets.main.allJava
                classpath = configurations.compile
            }

            tasks.create(name: 'javadocJar', type: Jar) {
                classifier = 'javadoc'
                from javadoc.destinationDir
            }

            artifacts {
                archives javadocJar
            }
        }
    }
}

class TalendRootPluginExtension {

}
