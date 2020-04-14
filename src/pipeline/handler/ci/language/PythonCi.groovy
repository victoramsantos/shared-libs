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
    String getBuildImage(String runtimeVersion) {
        return "python:$runtimeVersion-alpine"
    }

    @Override
    void buildAndTest(String runtimeVersion, String requirementsPath) {
        Pip pip = new Pip()
        pip.install(
                runtimeVersion,
                requirementsPath
        )
//        pytest.runTest(params.TEST_PATH)
    }
}
