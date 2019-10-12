import build.Pip
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

    pipeline {
        agent any
        options {
            skipStagesAfterUnstable()
        }
        stages {
            stage("Cloning") {
                steps {
                    script {
                        String ret = git.cloneAndCheckout(
                                params.REPO,
                                params.APPNAME,
                                params.BRANCH
                        )

                        this.println("Ret foi ${ret}")
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
                        script{
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
                agent any
                steps {
                    sh "tar -zcvf ${params.APPNAME}-${env.BUILD_NUMBER}.gz ${params.APPNAME}/"
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