package tool.test

import utils.ShellCommand

class Pytest extends ShellCommand{
    Pytest(Object steps) {
        super(steps)
    }
    void runTest(String testPath){
        super.exec("pytest ${params.TEST_PATH}")
    }
}
