package utils

class Sh {
    void exec(String command){
        sh "${command}"
    }

    void execOut(String command){
        String out = sh (
                script:  "${command}",
                returnStdout: true
        ).trim()
        echo out
    }

    String execWithReturn(String command){
        return sh (
                script:  "${command}",
                returnStdout: true
        )
    }
}
