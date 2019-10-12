import build.Pip
import tool.packing.Tar
import tool.sourcecontrolmanagement.Git
import tool.test.Pytest

def call(body) {
    def params = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = params
    body()

    Pip pip = new Pip(this)
    Pytest pytest = new Pytest(this)
    Git git = new Git(this)
    Tar tar = new Tar(this)

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
                        this.masterPath = git.cloneAndCheckout {
                            repository: params.REPO
                            appName: params.APPNAME
                            branch: params.BRANCH
                        }
                    }
                }
            }
            stage("Bulding") {
                agent {
                    docker {
                        image "python:${params.PYTHON_VERSION}-alpine"
                    }
                }
                steps {
                    dir("${params.REPO}") {
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