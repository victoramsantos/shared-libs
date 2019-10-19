import clouddeploy.Terraform
import tool.sourcecontrolmanagement.Git
import tool.sourcecontrolmanagement.SCM
import utils.Log

def call(body) {
    def params = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = params
    body()

    SCM scm = new Git(this)
    Log log = new Log(this)
    Terraform terraform = new Terraform(this)
    String masterPath


    pipeline {
        agent any
        options {
            skipStagesAfterUnstable()
        }
        stages {
            stage("Retrieving package") {
                steps {
                    script {
                        log.info("Using masterPath as")
                    }
                }
            }
            stage("Deploying app") {
                steps {
                    stages {
                        stage("Creating LC and ASG") {
                            steps {
                                log.info("Creating LC and ASG")
                            }
                        }
                        stage("Deploying app") {
                            steps{
                                log.info("Deploying app")
                                log.info("Testing health check")
                                log.info("Creating TG")
                                log.info("Creating ALBs if not already exist")
                                log.info("Creating Green ALB")
                                log.info("Changing flow to 90/10")
                                log.info("Changing flow to 70/30")
                                log.info("Changing flow to 50/50")
                                log.info("Changing flow to 0/100")
                                log.info("Promoting Green ALB to Blue")
                                log.info("Deleting old blue environment")
                            }
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