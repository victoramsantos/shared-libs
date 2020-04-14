package libs.tool.sourcecontrolmanagement


import static pipeline.context.PipelineContext.shell
import static pipeline.context.PipelineContext.shellWithReturn

class Git implements SCM{

    @Override
    String cloneAndCheckout(String repository, String applicationName, String branch) {
        //TODO Improve this!
        shell("git clone -b $branch $repository")
        shell(applicationName, "git checkout $branch")
        return shellWithReturn(applicationName, "pwd")
    }
}
