import libs.utils.ApplicationProperties
import libs.utils.Utils
import trigger.JobTrigger

def call(Map jobParams) {
    Map defaultProperties = Utils.parsePropertyFile(this, "default.properties")
    ApplicationProperties applicationProperties = new ApplicationProperties(defaultProperties, jobParams)

    pipeline {
        agent any
        stages {
            stage("works") {
                steps {
                    script {
                        for (Map.Entry entry : applicationProperties.getProperties().entrySet()) {
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
            success {
                script {
                    JobTrigger.trigger(this, applicationProperties)
                }
            }
        }
    }
}