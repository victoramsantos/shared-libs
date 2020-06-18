package pipeline.handler.ci.language

import libs.build.Pip
import libs.testtool.PyTest
import libs.utils.ApplicationProperties
import pipeline.handler.ci.GenericCi

class PythonCi extends GenericCi {

    PythonCi(ApplicationProperties applicationProperties) {
        super(applicationProperties)
    }

    @Override
    String getBuildImage() {
        String runtimeVersion = applicationProperties.getString("RUNTIME_VERSION")
        return "python:$runtimeVersion-alpine"
    }

    @Override
    void buildAndTest() {
        String requirementsPath = applicationProperties.getString("REQUIREMENTS_PATH")
        String testPath = applicationProperties.getString("TEST_PATH")

        Pip pip = new Pip()
        pip.install(requirementsPath)

        PyTest pyTest = new PyTest()
        pyTest.runTest(testPath)
    }
}
