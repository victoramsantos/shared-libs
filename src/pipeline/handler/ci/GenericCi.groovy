package pipeline.handler.ci

import libs.tool.sourcecontrolmanagement.Git
import libs.tool.sourcecontrolmanagement.SCM
import libs.utils.ApplicationProperties
import libs.utils.Log

abstract class GenericCi implements PipelineHandlerCi {

    private ApplicationProperties applicationProperties

    GenericCi(ApplicationProperties applicationProperties){
        this.applicationProperties = applicationProperties
    }

    @Override
    void scmClone() {
        String repository = this.applicationProperties.getString("REPOSITORY")
        String applicationName = this.applicationProperties.getString("APPLICATION_NAME")
        String branch = this.applicationProperties.getString("BRANCH")

        SCM scm = new Git()
        String masterPath = scm.cloneAndCheckout(
                repository,
                applicationName,
                branch
        )
        Log.info("Using masterPath as $masterPath")
        this.applicationProperties.add("APPLICATION_PATH", masterPath)
    }

    @Override
    void codeQualityAnalysis() {

    }

    @Override
    void buildImage() {

    }
}
