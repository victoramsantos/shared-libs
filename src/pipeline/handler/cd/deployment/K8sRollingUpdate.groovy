package pipeline.handler.cd.deployment

import libs.utils.ApplicationProperties
import pipeline.handler.cd.GenericCd

class K8sRollingUpdate extends GenericCd {

    K8sRollingUpdate(ApplicationProperties applicationProperties) {
        super(applicationProperties)
    }
}
