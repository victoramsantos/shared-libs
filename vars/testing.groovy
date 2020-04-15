import libs.utils.ApplicationProperties
import libs.utils.Utils

def call(Map jobParams) {
    Map defaultProperties = Utils.parsePropertyFile(this,"default.properties")
    ApplicationProperties applicationProperties = new ApplicationProperties(defaultProperties, jobParams)

    pipeline {
        agent any
        stages {
            stage("works") {
                steps {
                    script {
                        for (Map.Entry entry : applicationProperties.getApplicationProperties().entrySet()) {
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
            success{
                loadScript("vars/testingcd.groovy").call(applicationProperties)

//                JobTrigger.trigger()
            }
        }
    }
}