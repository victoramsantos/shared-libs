import libs.clouddeploy.Terraform
import libs.utils.Log

def call(body) {
    def params = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = params
    body()

    Log log = new Log(this)
    Terraform terraform = new Terraform(this)
    String terraformPath


    pipeline {
        agent any
        options {
            skipStagesAfterUnstable()
        }
        stages {
            stage("Retrieving util libs") {
                steps {
                    script {
                        script {
                            log.info("Retrieving terraform repository")
                            terraformPath = terraform.loads()
                            log.info("Using terraform from ${terraformPath}")
                        }
                    }
                }
            }
            stage("Deploying app") {
                stages {
                    stage("Creating LC and ASG") {
                        steps {
                            script {
                                log.info("Creating LC and ASG")
                                terraform.createASGStack()
                            }
                        }
                    }
                    stage("Deploying app") {
                        steps {
                            script {
                                log.info("Retrieving package")
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