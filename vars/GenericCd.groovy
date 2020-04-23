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
                        pipelineHandler.scmClone()
                    }
                }
            }
            stage("Creating service") {
                steps {
                    script {
                        pipelineHandler.creatingService()
                    }
                }
            }
        }
        stages {
            stage("Applying deployment") {
                steps {
                    script {
                        pipelineHandler.applyingDeployment()
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