package tool

import utils.Sh

class Git extends Sh{
    String cloneAndCheckout(String REPO, String REPO_NAME, String BRANCH){
        exec("git clone -b ${BRANCH} ${REPO} && cd ${REPO_NAME} && git checkout ${BRANCH}")
        return super.execWithReturn("cd ${REPO_NAME} && pwd")
    }
}
