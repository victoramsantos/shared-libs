package clouddeploy

import handler.PipelineHandler
import tool.sourcecontrolmanagement.Git
import tool.sourcecontrolmanagement.SCM

class Terraform extends PipelineHandler {
    private String repository
    private String appName
    private SCM scm
    private masterPath

    Terraform(Object pipelineContext) {
        super(pipelineContext)

        scm = new Git(pipelineContext)
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
