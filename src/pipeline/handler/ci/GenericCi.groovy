package pipeline.handler.ci

import libs.tool.sourcecontrolmanagement.Git
import libs.tool.sourcecontrolmanagement.SCM
import libs.utils.ApplicationProperties
import libs.utils.Log

abstract class GenericCi implements PipelineHandlerCi {

    private ApplicationProperties properties

    GenericCi(ApplicationProperties properties){
        this.properties = properties
    }

    @Override
    void scmClone(String repository, String applicationName, String branch) {
        SCM scm = new Git()
        String masterPath = scm.cloneAndCheckout(
                repository,
                applicationName,
                branch
        )
        Log.info("Using masterPath as $masterPath")
    }

    @Override
    void buildAndTest() {

    }

    @Override
    void codeQualityAnalysis() {

    }

    @Override
    void buildImage() {

    }
}
