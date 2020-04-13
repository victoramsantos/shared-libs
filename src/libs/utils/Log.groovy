package libs.utils

import libs.handler.PipelineHandler

class Log extends PipelineHandler{

    Log(Object pipelineContext) {
        super(pipelineContext)
    }

    void info(String message){
        this.logging("INFO", message)
    }

    void warning(String message){
        this.logging("WARNING", message)
    }

    void error(String message){
        this.logging("ERROR", message)
    }

    private void logging(String severity, String message){
        pipelineContext.echo "[${severity}] ${message}"
    }

}
