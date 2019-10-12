package tool.sourcecontrolmanagement

import utils.ShellCommand

class Git extends ShellCommand{

    Git(steps) {
        super(steps)
    }

    String cloneAndCheckout(String repository, String appName, String branch) {
        super.exec("git clone -b ${BRANCH} ${repository} && cd ${appName} && git checkout ${branch}")
        return super.execWithReturn("cd ${appName} && pwd")
    }
}
