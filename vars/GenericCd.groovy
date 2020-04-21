import libs.utils.ApplicationProperties
import libs.utils.Utils
import pipeline.handler.cd.PipelineHandlerCd
import pipeline.handler.cd.PipelineHandlerCdFactory

def call(Map jsonMap) {
    ApplicationProperties applicationProperties = new ApplicationProperties(Utils.parseJsonMapToMap(jsonMap))
    PipelineHandlerCd pipelineHandler = PipelineHandlerCdFactory.build(this, applicationProperties)

    pipeline {
        agent any
        stages {
            stage("Cloning project") {
                steps {
                    script {
                        echo "${applicationProperties.toString()}"
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