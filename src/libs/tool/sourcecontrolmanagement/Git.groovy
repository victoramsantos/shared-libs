package libs.tool.sourcecontrolmanagement


import static pipeline.context.PipelineContext.shell
import static pipeline.context.PipelineContext.shellWithReturn

class Git implements SCM{
    private final int shortHasCommitSize = 4

    @Override
    String cloneAndCheckout(String repository, String applicationName, String branch) {
        //TODO Improve this!
        shell("git clone -b $branch $repository")
        shell(applicationName, "git checkout $branch")
        return shellWithReturn(applicationName, "pwd")
    }

    String getShortHashCommit(String applicationPath, String branch){
        String hashCommit = shellWithReturn(applicationPath, "git rev-parse origin/$branch")
        return hashCommit.substring(0, shortHasCommitSize)
    }
}
