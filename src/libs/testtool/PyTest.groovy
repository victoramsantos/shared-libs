package libs.testtool

import static pipeline.context.PipelineContext.shell

class PyTest {

    void runTest(String testPath){
        shell("python -m pytest $testPath")
    }
}
