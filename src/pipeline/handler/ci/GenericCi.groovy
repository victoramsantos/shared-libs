package pipeline.handler.ci

import libs.docker.Docker
import libs.docker.DockerEcr
import libs.utils.ApplicationProperties
import pipeline.handler.PipelineHandlerUtils

abstract class GenericCi implements PipelineHandlerCi {

    protected ApplicationProperties applicationProperties

    GenericCi(ApplicationProperties applicationProperties){
        this.applicationProperties = applicationProperties
    }

    @Override
    void scmClone() {
        PipelineHandlerUtils.scmClone(this.applicationProperties)
    }

    @Override
    void buildDockerImage() {
        String repository = this.applicationProperties.getString("ECR_REPOSITORY")
        String applicationName = this.applicationProperties.getString("APPLICATION_NAME")
        String tag = this.applicationProperties.getString("SHORT_HASH_COMMIT")
        String region = this.applicationProperties.getString("REGION")

        Docker docker = new DockerEcr()

        docker.login(region)
        docker.createRepository(repository, applicationName, region)
        docker.build(repository, applicationName, tag)
        docker.push(repository, applicationName, tag)
    }
}
