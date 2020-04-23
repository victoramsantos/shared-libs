package pipeline.handler.cd

import libs.kubectl.Kubectl
import libs.utils.ApplicationProperties
import libs.utils.Utils
import pipeline.context.PipelineContext
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
    void creatingService() {
        String applicationPath = this.applicationProperties.getString("APPLICATION_PATH")
        String serviceFilePath = this.applicationProperties.getString("SERVICE_FILE_PATH")

        String filePath = applicationPath + serviceFilePath

        PipelineContext.shell("kubectl get nodes")

        Kubectl kubectl = new Kubectl()
        kubectl.applyService(filePath)
    }

    @Override
    void applyingDeployment() {
        String imageRepository = this.applicationProperties.getString("ECR_REPOSITORY")
        String tag = this.applicationProperties.getString("SHORT_HASH_COMMIT")
        String applicationPath = this.applicationProperties.getString("APPLICATION_PATH")
        String deploymentFilePath = this.applicationProperties.getString("DEPLOYMENT_FILE_PATH")

        String filePath = applicationPath + deploymentFilePath

        Utils.sed(
                filePath,
                "\$REGISTRY",
                imageRepository
        )

        Utils.sed(
                filePath,
                "\$TAG",
                tag
        )

        Kubectl kubectl = new Kubectl()
        kubectl.applyDeployment(filePath)
    }
}
