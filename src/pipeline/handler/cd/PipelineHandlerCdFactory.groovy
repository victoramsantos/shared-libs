package pipeline.handler.cd

import libs.utils.ApplicationProperties
import pipeline.context.PipelineContext
import pipeline.handler.cd.deployment.K8sRollingUpdate

class PipelineHandlerCdFactory {

    static PipelineHandlerCd build(def context, ApplicationProperties applicationProperties){
        PipelineContext.init(context)
        PipelineHandlerCd pipelineHandlerCd
        CdTypes cdTypes = CdTypes.valueOf(applicationProperties.getString("DEPLOYMENT_TYPE"))

        switch (cdTypes){
            case CdTypes.K8S_ROLLING_UPDATE:
                pipelineHandlerCd = new K8sRollingUpdate(applicationProperties)
                break
        }

        return pipelineHandlerCd
    }
}
