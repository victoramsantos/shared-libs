package build

import utils.PipelineHandler

class Pip extends PipelineHandler{
    Pip(Object steps) {
        super(steps)
    }

    void install(String pythonVersion, String requirements="requirements"){
        super.execOut("pip${pythonVersion} install -r ${requirements} -t .")
    }
}
