def call() {
    def params = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = params
    body()

    pipeline {
        agent none
        options {
            skipStagesAfterUnstable()
        }
        stages {
            stage("Clonning ${params.APPNAME}") {
                agent any
                steps {
                    script {
                        sh "git clone ${params.REPO}"
                        sh "git checkout ${params.BRANCH}"
                    }
                }
            }
            stage("Bulding ${params.APPNAME}") {
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
            stage("Testing ${params.APPNAME}") {
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
            stage("Packing ${params.APPNAME}") {
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