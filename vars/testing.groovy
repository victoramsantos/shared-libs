import libs.utils.ApplicationProperties
import libs.utils.Utils
import trigger.JobTrigger

import static trigger.JobTrigger.trigger

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
                    trigger(this, applicationProperties)
                }
            }
        }
    }
}