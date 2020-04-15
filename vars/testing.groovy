import libs.utils.ApplicationProperties

def call(Map jobParams) {
    ApplicationProperties applicationProperties = new ApplicationProperties(
            ApplicationProperties.parseKeyValueStringToMap(
                    libraryResource('default.properties') as String
            ),
            jobParams
    )

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