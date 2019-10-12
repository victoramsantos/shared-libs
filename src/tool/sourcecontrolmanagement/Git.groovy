package tool.sourcecontrolmanagement

import utils.PipelineHandler

class Git extends PipelineHandler{

    Git(steps) {
        super(steps)
    }

    String cloneAndCheckout(String repository, String appName, String branch="master") {
        super.exec("git clone -b ${branch} ${repository} && cd ${appName} && git checkout ${branch}")
        return super.execWithReturn("cd ${appName} && pwd")
    }
}
