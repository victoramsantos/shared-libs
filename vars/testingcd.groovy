def call(Map map) {
    print("testingcd")
    pipeline {
        agent any
        stages {
            stage("works") {
                steps {
                    script {
                        for (Map.Entry entry : map.entrySet()) {
                            print("${entry.key} == ${entry.value}")
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