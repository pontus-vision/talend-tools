package org.talend.buildtools

import org.gradle.api.Plugin
import org.gradle.api.Project

class TalendRootPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.task('helloWorld') << {
            println "Hello from the TalendRootPlugin"
        }
    }
}