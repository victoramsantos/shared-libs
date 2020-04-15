package pipeline.handler.cd


import libs.utils.ApplicationProperties

abstract class GenericCd implements PipelineHandlerCd {

    protected ApplicationProperties applicationProperties

    GenericCd(ApplicationProperties applicationProperties){
        this.applicationProperties = applicationProperties
    }

}
