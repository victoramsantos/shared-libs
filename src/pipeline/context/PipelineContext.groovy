package pipeline.context

@Singleton(strict = false)
class PipelineContext implements Serializable {
    private static def pipelineContext

    PipelineContext(pipelineContext) {
        this.pipelineContext = pipelineContext
    }

    static void shell(String command) {
        pipelineContext.sh command
    }

    static void shell(String directory, String command) {
        pipelineContext.sh "cd $directory && $command"
    }

    static String shellWithReturn(String command) {
        return pipelineContext.sh(
                script: "$command",
                returnStdout: true
        ).trim()
    }

    static String shellWithReturn(String directory, String command) {
        return pipelineContext.sh(
                script: "cd $directory && $command",
                returnStdout: true
        ).trim()
    }

    static void echo(String content) {
        pipelineContext.echo "$content"
    }

    static void init(def context) {
        pipelineContext = context
    }
}
