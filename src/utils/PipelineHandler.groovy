package utils

class PipelineHandler implements Serializable {
    def steps

    PipelineHandler(steps) {
        this.steps = steps
    }

    void exec(String command) {
        steps.sh command
    }

    void execOut(String command) {
        String out = steps.sh(
                script: "${command}",
                returnStdout: true
        ).trim()
        steps.echo out
    }

    String execWithReturn(String command) {
        return steps.sh(
                script: "${command}",
                returnStdout: true
        )
    }
}
