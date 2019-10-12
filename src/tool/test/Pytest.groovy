package tool.test

import handler.PipelineHandler

class Pytest extends PipelineHandler{
    Pytest(Object pipelineContext) {
        super(pipelineContext)
    }
    void runTest(String testPath){
        super.exec("pytest ${testPath}")
    }
}
