package pipeline.handler.cd

import libs.kubectl.Kubectl
import libs.utils.ApplicationProperties
import libs.utils.Utils
import pipeline.handler.PipelineHandlerUtils

abstract class GenericCd implements PipelineHandlerCd {

    protected ApplicationProperties applicationProperties

    GenericCd(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties
    }

    @Override
    void scmClone() {
        PipelineHandlerUtils.scmClone(this.applicationProperties)
    }

    @Override
    void applyingDeployment() {
        String imageRepository = this.applicationProperties.getString("ECR_REPOSITORY")
        String tag = this.applicationProperties.getString("SHORT_HASH_COMMIT")
        String applicationPath = this.applicationProperties.getString("APPLICATION_PATH")
        String serviceFilePath = this.applicationProperties.getString("SERVICE_FILE_PATH")
        String deploymentFilePath = this.applicationProperties.getString("DEPLOYMENT_FILE_PATH")

        Kubectl kubectl = new Kubectl()

        String service = applicationPath + serviceFilePath
        String deployment = applicationPath + deploymentFilePath

        Utils.sed(
                deployment,
                "\$REGISTRY",
                imageRepository
        )

        Utils.sed(
                deployment,
                "\$TAG",
                tag
        )

        kubectl.applyService(service)
        kubectl.applyDeployment(deployment)
    }
}
