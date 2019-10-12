package tool.test

import utils.PipelineHandler

class Pytest extends PipelineHandler{
    Pytest(Object steps) {
        super(steps)
    }
    void runTest(String testPath){
        super.exec("pytest ${params.TEST_PATH}")
    }
}
