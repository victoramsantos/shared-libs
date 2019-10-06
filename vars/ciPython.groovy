def call(){
    pipeline {
        agent none
        options {
            skipStagesAfterUnstable()
        }
        environment {
            git_token = ''
            repo_name = 'placeholder'
            branch = 'master'
        }
        stages {
            stage('Clone') {
                agent any
                steps {
                    script {
                        if (fileExists("${repo_name}")) {
                            dir("${repo_name}") {
                                sh "git pull origin ${branch}"
                            }
                        }else {
                            sh "git clone https://${git_token}@github.com/victoramsantos/${repo_name}.git"
                        }
                    }
                }
            }
            stage('Build') {
                agent {
                    docker {
                        image 'python:3.6-alpine'
                    }
                }
                steps {
                    dir("${repo_name}") {
                        sh 'pip3.6 install -r requirements -t .'
                    }
                }
            }
            stage('Test') {
                agent {
                    docker {
                        image 'python:3.6-alpine'
                    }
                }
                steps {
                    dir("${repo_name}") {
                        sh 'python3.6 -m pytest test/'
                    }
                }
            }
            stage('Pack') {
                agent any
                steps {
                    dir("${repo_name}") {
                        zip zipFile: "placeholder-${env.BUILD_NUMBER}.zip"
                    }
                }
            }
//            stage('Uploading to S3') {
//                agent any
//                steps {
//                    dir("${repo_name}") {
//                        withAWS(region:'us-east-1', credentials:'gb-playland-system') {
//                            s3Upload(bucket:"victoramsantos-ci-cd", file:"placeholder-${env.BUILD_NUMBER}.zip");
//                        }
//                    }
//                }
//            }
            // stage ('Starting CD job') {
            //     steps {
            //         script {
            //             build job: 'placeholder-deploy/cd-placeholder'
            //         }
            //     }
            // }

        }
    }
}