package build

import handler.PipelineHandler

class Pip extends PipelineHandler{
    Pip(Object pipelineContext) {
        super(pipelineContext)
    }

    void install(String pythonVersion, String requirements="requirements"){
        super.execOut("pip${pythonVersion} install -r ${requirements} -t .")
    }
}
