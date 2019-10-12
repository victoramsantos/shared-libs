package tool

import utils.ShellCommand

class Git extends ShellCommand{

    Git(steps) {
        super(steps)
    }

    String cloneAndCheckout(String REPO, String REPO_NAME, String BRANCH) {

            steps.echo ("cloneAndChekcout aqui")
            exec("git clone -b ${BRANCH} ${REPO} && cd ${REPO_NAME} && git checkout ${BRANCH}")
            return super.execWithReturn("cd ${REPO_NAME} && pwd")

    }
}
