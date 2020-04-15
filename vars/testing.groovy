import libs.utils.ApplicationProperties

def call(Map jobParams) {
    Map defaultProperties = readProperties text: 'resources/default.properties'
    ApplicationProperties applicationProperties = new ApplicationProperties(defaultProperties, jobParams)

    pipeline {
        agent any
        stages {
            stage("works") {
                steps {
                    script {
                        for (Map.Entry entry : applicationProperties.testing()) {
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