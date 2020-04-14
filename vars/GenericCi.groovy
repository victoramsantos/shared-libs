import libs.utils.ApplicationProperties
import pipeline.context.PipelineContext
import pipeline.handler.ci.PipelineHandlerCi
import pipeline.handler.ci.PipelineHandlerCiFactory

def call(body) {
    def params = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = params
    body()

    PipelineContext.init(this)
    ApplicationProperties applicationProperties = new ApplicationProperties(params)
    PipelineHandlerCi pipelineHandler = PipelineHandlerCiFactory.build(applicationProperties)

    pipeline {
        agent any
        stages {
            stage("Cloning Project") {
                steps {
                    script {
                        pipelineHandler.scmClone(
                                applicationProperties.getString("REPOSITORY"),
                                applicationProperties.getString("APPLICATION_NAME"),
                                applicationProperties.getString("BRANCH")
                        )
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