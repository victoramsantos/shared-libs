def call(Map jobParams) {

    for(Map.Entry entry: jobParams){
        print("${entry.key} == ${entry.value}")
    }

    pipeline {
        agent any
        stages {
            stage("works?") {
                steps {
                    script {
                        for(Map.Entry entry: jobParams){
                            echo "${entry.key} == ${entry.value}"
                        }
                    }
                }
            }
        }
        post {
            always {
                cleanWs()
            }
        }
    }
}