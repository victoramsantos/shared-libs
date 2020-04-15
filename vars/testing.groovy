def call(Map jobParams) {

    print(jobParams["PIPELINE_CI"])
//    def params = [:]
//    body.resolveStrategy = Closure.DELEGATE_FIRST
//    body.delegate = params
//    body()

    pipeline {
        agent any
        stages {
            stage("works?") {
                steps {
                    script {
                        error "stopping gere"
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