import libs.utils.ApplicationProperties
import libs.utils.Utils

def call(Map jobParams) {
    Map defaultProperties = Utils.parsePropertyFile("default.properties")
    ApplicationProperties applicationProperties = new ApplicationProperties(defaultProperties,jobParams)

    pipeline {
        agent any
        stages {
            stage("works") {
                steps {
                    script {
                        for (Map.Entry entry : applicationProperties.testing().entrySet()) {
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