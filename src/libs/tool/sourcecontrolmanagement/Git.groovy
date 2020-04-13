package libs.tool.sourcecontrolmanagement

import libs.handler.PipelineHandler

class Git extends PipelineHandler implements SCM{

    Git(pipelineContext) {
        super(pipelineContext)
    }

    @Override
    String cloneAndCheckout(String repository, String appName, String branch="master") {
        super.exec("git clone -b ${branch} ${repository} && cd ${appName} && pwd && git checkout ${branch}")
        return super.execWithReturn("cd ${appName} && pwd").trim()
    }
}
