package pipeline.handler.ci.language

import libs.build.Pip
import libs.utils.ApplicationProperties
import pipeline.handler.ci.GenericCi

class PythonCi extends GenericCi {

    protected ApplicationProperties applicationProperties

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
        Pip pip = new Pip()
        String runtimeVersion = applicationProperties.getString("RUNTIME_VERSION")
        String requirementsPath = applicationProperties.getString("REQUIREMENTS_PATH")


        pip.install(
                runtimeVersion,
                requirementsPath
        )
//        pytest.runTest(params.TEST_PATH)
    }
}
