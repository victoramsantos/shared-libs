import libs.utils.ApplicationProperties
import pipeline.context.PipelineContext
import pipeline.handler.ci.PipelineHandlerCi
import pipeline.handler.ci.PipelineHandlerCiFactory

def call(Map jobParams) {
    Map defaultProperties = readProperties text: libraryResource 'resources/default.properties'
    ApplicationProperties applicationProperties = new ApplicationProperties(defaultProperties, jobParams)
    PipelineHandlerCi pipelineHandler = PipelineHandlerCiFactory.build(applicationProperties)
    PipelineContext.init(this)

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
            stage("Building and testing") {
                agent {
                    docker {
                        image "${pipelineHandler.getBuildImage()}"
                        args "-v ${WORKSPACE}:${WORKSPACE} -w ${WORKSPACE}"
                    }
                }
                steps {
                    dir("${applicationProperties.getString("APPLICATION_PATH")}") {
                        script {
                            pipelineHandler.buildAndTest()
                        }
                    }
                }
            }
            stage("Building image") {
                steps {
                    dir("${applicationProperties.getString("APPLICATION_PATH")}") {
                        script{
                            pipelineHandler.buildDockerImage()
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