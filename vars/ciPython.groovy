import libs.build.Pip
import libs.testtool.Pytest
import libs.tool.cloudprovider.Aws
import libs.tool.cloudprovider.CloudProvider
import libs.tool.packing.Packing
import libs.tool.packing.Tar
import libs.tool.sourcecontrolmanagement.Git
import libs.tool.sourcecontrolmanagement.SCM
import libs.utils.Log
import libs.utils.Shell

def call(body) {
    def params = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = params
    body()

    Pip pip = new Pip(this)
    Pytest pytest = new Pytest(this)
    SCM scm = new Git(this)
    Packing packing = new Tar(this)
    Log log = new Log(this)
    Shell shell = new Shell(this)
    CloudProvider cloud = new Aws(this)

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
                        masterPath = scm.cloneAndCheckout(
                                params.REPO,
                                params.APPNAME,
                                params.BRANCH
                        )

                        log.info("Using masterPath as ${masterPath}")
                    }
                }
            }
            stage("Building and Testing") {
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
                            pytest.runTest(params.TEST_PATH)
                        }
                    }
                }
            }
            stage("Building Image") {
                steps {
                    script {
                        log.info("Should build container image")
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