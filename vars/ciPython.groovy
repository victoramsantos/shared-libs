def call(body) {
    def params = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = params
    body()

    pipeline {
        agent any
        options {
            skipStagesAfterUnstable()
        }
        stages {
            stage("Cloning") {
                steps {
                    sh "git clone ${params.REPO}"
                    sh "git checkout ${params.BRANCH}"
                }
            }
            stage("Bulding") {
                agent {
                    docker {
                        image "python:${params.PYTHON_VERSION}-alpine"
                    }
                }
                steps {
                    dir("${repo_name}") {
                        sh "pip${params.PYTHON_VERSION} install -r requirements -t ."
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
                    dir("${params.APPNAME}") {
                        sh "python${params.APPNAME} -m pytest ${params.TEST_PATH}"
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