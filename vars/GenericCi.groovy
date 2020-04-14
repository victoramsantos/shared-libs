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
    print(params.PIPELINE_CI)
    ApplicationProperties properties = new ApplicationProperties(params)
    print(properties.getString("PIPELINE_CI"))
    print(properties.getString("REPOSITORY"))
    PipelineHandlerCi pipelineHandler = PipelineHandlerCiFactory.build(properties)

    pipeline {
        stages {
            stage("Cloning") {
                steps {
                    script {
                        pipelineHandler.scmClone(
                                properties.getString("REPOSITORY"),
                                properties.getString("APPLICATION_NAME"),
                                properties.getString("BRANCH")
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