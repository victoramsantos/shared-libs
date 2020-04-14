package pipeline.handler.ci

import libs.docker.Docker
import libs.docker.DockerEcr
import libs.tool.sourcecontrolmanagement.Git
import libs.tool.sourcecontrolmanagement.SCM
import libs.utils.ApplicationProperties
import libs.utils.Log

abstract class GenericCi implements PipelineHandlerCi {

    protected ApplicationProperties applicationProperties

    GenericCi(ApplicationProperties applicationProperties){
        this.applicationProperties = applicationProperties
    }

    @Override
    void scmClone() {
        String repository = this.applicationProperties.getString("REPOSITORY")
        String applicationName = this.applicationProperties.getString("APPLICATION_NAME")
        String branch = this.applicationProperties.getString("BRANCH")

        SCM scm = new Git()
        String applicationPath = scm.cloneAndCheckout(
                repository,
                applicationName,
                branch
        )
        String shortHashCommit = scm.getShortHashCommit(applicationPath, branch)
        Log.info("Using applicationPath as $applicationPath with shortHashCommit: $shortHashCommit")
        this.applicationProperties.add("APPLICATION_PATH", applicationPath)
        this.applicationProperties.add("SHORT_HASH_COMMIT", shortHashCommit)
    }

    @Override
    void buildDockerImage() {
        String repository = "397823652972.dkr.ecr.us-east-1.amazonaws.com"
        String applicationName = this.applicationProperties.getString("APPLICATION_NAME")
        String tag = this.applicationProperties.getString("SHORT_HASH_COMMIT")
        String region = "us-east-1"

        Docker docker = new DockerEcr()

        docker.login(region)
        docker.createRepository(repository, applicationName, region)
        docker.build(repository, applicationName, tag)
        docker.push(repository, applicationName, tag)
    }
}
