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
            stage("Cloning project") {
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
            stage("Building and testing") {
                agent {
                    docker {
                        image "${pipelineHandler.getBuildImage(applicationProperties.getString("RUNTIME_VERSION"))}"
                        args "-v ${WORKSPACE}:${WORKSPACE} -w ${WORKSPACE}"
                    }
                }
                steps {
                    dir("${masterPath}") {
                        script {
                            pipelineHandler.buildAndTest(
                                    applicationProperties.getString("RUNTIME_VERSION"),
                                    applicationProperties.getString("REQUIREMENTS_PATH")
                            )
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