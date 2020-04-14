package pipeline.handler.ci

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
        String repository = applicationProperties.getString("REPOSITORY")
        String applicationName = applicationProperties.getString("APPLICATION_NAME")
        String branch = applicationProperties.getString("BRANCH")

        SCM scm = new Git()
        String masterPath = scm.cloneAndCheckout(
                repository,
                applicationName,
                branch
        )
        Log.info("Using masterPath as $masterPath")
        applicationProperties.add("APPLICATION_PATH", masterPath)
    }

    @Override
    void codeQualityAnalysis() {

    }

    @Override
    void buildImage() {

    }
}
