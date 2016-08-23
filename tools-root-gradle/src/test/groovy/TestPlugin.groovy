import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class BuildLogicFunctionalTest extends Specification {
    @Rule
    final TemporaryFolder testProjectDir = new TemporaryFolder(File.createTempDir())
    File buildFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
    }

    def setupTestFile(fileName) {
        InputStream is = getClass().getResourceAsStream(fileName)
        def fos = new FileOutputStream(buildFile.getAbsolutePath())
        fos << is
        fos.close()
    }

    def "basic test"() {
        given:
        setupTestFile("test1.gradle")

        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .withArguments('helloWorld')
                .build()

        then:
        result.output.contains(':helloWorld')
        result.task(":helloWorld").outcome == SUCCESS
    }
}
