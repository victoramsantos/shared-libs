package libs.handler

class PipelineHandler implements Serializable {
    def pipelineContext

    PipelineHandler(pipelineContext) {
        this.pipelineContext = pipelineContext
    }

    void exec(String command) {
        pipelineContext.sh command
    }

    void execOut(String command) {
        String out = pipelineContext.sh(
                script: "${command}",
                returnStdout: true
        ).trim()
        pipelineContext.echo out
    }

    String execWithReturn(String command) {
        return pipelineContext.sh(
                script: "${command}",
                returnStdout: true
        )
    }
}
