
class TalendPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.task('hello') << {
            println "Hello from the TalendPlugin"
        }
    }
}