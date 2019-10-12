package utils

class ShellCommand implements Serializable {
    def steps

    ShellCommand(steps) {
        this.steps = steps
    }

    void exec(String command) {
        steps.sh command
    }

    void execOut(String command) {
        steps.script {
            String out = sh(
                    script: "${command}",
                    returnStdout: true
            ).trim()
            echo out
        }
    }

    String execWithReturn(String command) {
        steps.script {
            return sh(
                    script: "${command}",
                    returnStdout: true
            )
        }
    }

    void print(String str){
        steps.echo str
    }
}
