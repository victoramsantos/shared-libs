package pipeline.handler

import libs.tool.sourcecontrolmanagement.Git
import libs.tool.sourcecontrolmanagement.SCM
import libs.utils.ApplicationProperties
import libs.utils.Log

class PipelineHandlerUtils {
    static void scmClone(ApplicationProperties applicationProperties) {
        String repository = applicationProperties.getString("REPOSITORY")
        String applicationName = applicationProperties.getString("APPLICATION_NAME")
        String branch = applicationProperties.getString("BRANCH")

        SCM scm = new Git()
        String applicationPath = scm.cloneAndCheckout(
                repository,
                applicationName,
                branch
        ) + "/"
        String shortHashCommit = scm.getShortHashCommit(applicationPath, branch)
        Log.info("Using applicationPath as $applicationPath with shortHashCommit: $shortHashCommit")
        applicationProperties.add("APPLICATION_PATH", applicationPath)
        applicationProperties.add("SHORT_HASH_COMMIT", shortHashCommit)
    }

}
