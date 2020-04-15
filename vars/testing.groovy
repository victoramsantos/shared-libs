def call(Map jobParams) {

    for(Map.Entry entry: jobParams){
        print("${entry.key} == ${entry.value}")
    }
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