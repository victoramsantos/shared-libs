import libs.utils.ApplicationProperties
import libs.utils.Utils

def call(Map jsonMap) {
    ApplicationProperties applicationProperties = new ApplicationProperties(Utils.p)
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