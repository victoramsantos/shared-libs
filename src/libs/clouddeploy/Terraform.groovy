package libs.clouddeploy

import libs.tool.sourcecontrolmanagement.Git
import libs.tool.sourcecontrolmanagement.SCM

class Terraform {
    private String repository
    private String appName
    private SCM scm
    private masterPath

    Terraform() {
        scm = new Git()
        this.repository = "git@github.com:victoramsantos/terraform.git"
        this.appName = "terraform"
    }

    String loads(String branch = "master") {
        this.masterPath = scm.cloneAndCheckout(
                this.repository,
                this.appName,
                branch
        )
        return this.masterPath
    }

    void createASGStack() {

    }
}
