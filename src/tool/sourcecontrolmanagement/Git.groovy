package tool.sourcecontrolmanagement

import handler.PipelineHandler

class Git extends PipelineHandler{

    Git(pipelineContext) {
        super(pipelineContext)
    }

    String cloneAndCheckout(String repository, String appName, String branch="master") {
        super.exec("pwd && git clone -b ${branch} ${repository} && cd ${appName} && pwd && git checkout ${branch}")
        return super.execWithReturn("pwd && cd ${appName} && pwd").trim()
    }
}
