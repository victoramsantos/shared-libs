package utils

class Sh {
    void exec(String command){
        script {
            sh "${command}"
        }
    }

    void execOut(String command){
        script {
            String out = sh(
                    script: "${command}",
                    returnStdout: true
            ).trim()
            echo out
        }
    }

    String execWithReturn(String command){
        script {
            return sh(
                    script: "${command}",
                    returnStdout: true
            )
        }
    }
}
