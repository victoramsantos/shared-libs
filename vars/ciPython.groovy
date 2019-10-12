import build.Pip
import tool.packing.Tar
import tool.sourcecontrolmanagement.Git
import tool.test.Pytest
import utils.Log

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

    String masterPath

    pipeline {
        agent any
        options {
            skipStagesAfterUnstable()
        }
        stages {
            stage("Cloning") {
                steps {
                    script {
                        this.masterPath = git.cloneAndCheckout(
                                params.REPO,
                                params.APPNAME,
                                params.BRANCH
                        )

                        log.info("Using masterPath as ${this.masterPath}")
                    }
                }
            }
            stage("Bulding") {
                agent {
                    docker {
                        image "python:${params.PYTHON_VERSION}-alpine"
                        args "-v ${masterPath}:${masterPath}"
                    }
                }
                steps {
                    dir(masterPath) {
                        script {
                            sh "ls -lha"
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
                        args "-v ${masterPath}:${masterPath} -w ${masterPath}"
                    }
                }
                steps {
                    script {
                        dir("${params.APPNAME}") {
                            pytest.runTest(params.TEST_PATH)
                        }
                    }
                }
            }
            stage("Packing") {
                steps {
                    script {
                        tar.packing(
                                params.APPNAME,
                                env.BUILD_NUMBER
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