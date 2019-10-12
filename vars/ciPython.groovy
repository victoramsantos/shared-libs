import build.Pip
import tool.packing.Tar
import tool.sourcecontrolmanagement.Git
import tool.test.Pytest
import utils.Log
import utils.Shell
import tool.aws.Aws

def call(body) {
    def params = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = params
    body()

    Pip pip = new Pip(this)
    Pytest pytest = new Pytest(this)
    Git git = new Git(this)
    Tar tar = new Tar(this)
    Log log = new Log(this)
    Shell shell = new Shell(this)
    Aws aws = new Aws(this)

    String masterPath
    String packedName

    pipeline {
        agent any
        options {
            skipStagesAfterUnstable()
        }
        stages {
            stage("Cloning") {
                steps {
                    script {
                        masterPath = git.cloneAndCheckout(
                                params.REPO,
                                params.APPNAME,
                                params.BRANCH
                        )

                        log.info("Using masterPath as ${masterPath}")
                    }
                }
            }
            stage("Building") {
                agent {
                    docker {
                        image "python:${params.PYTHON_VERSION}-alpine"
                        args "-v ${WORKSPACE}:${WORKSPACE} -w ${WORKSPACE}"
                    }
                }
                steps {
                    dir("${masterPath}") {
                        script {
                            pip.install(
                                    params.PYTHON_VERSION,
                                    params.REQUIREMENTS
                            )
                        }
                    }
                }
            }
            stage("Testing") {
                agent {
                    docker {
                        image "python:${params.PYTHON_VERSION}-alpine"
                        args "-v ${WORKSPACE}:${WORKSPACE} -w ${WORKSPACE}"
                    }
                }
                steps {
                    script {
                        dir("${masterPath}") {
                            script {
                                pip.installDependency(
                                        params.PYTHON_VERSION,
                                        "pytest"
                                )
                                pytest.runTest(params.TEST_PATH)
                            }
                        }
                    }
                }
            }
            stage("Packing") {
                steps {
                    script {
                        packedName = tar.packing(
                                params.APPNAME,
                                env.BUILD_NUMBER
                        )
                        log.info("App packed in ${shell.execWithReturn("pwd")}")
                    }
                }
            }
            stage("Uploading to S3") {
                steps {
                    script {
                        aws.uploadToS3(
                            packedName,
                            packedName,
                            params.APPNAME
                        )
                        log.info("Packing sent to S3")
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