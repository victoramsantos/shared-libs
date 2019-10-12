package build

import utils.ShellCommand

class Pip extends ShellCommand{
    Pip(Object steps) {
        super(steps)
    }

    void install(String pythonVersion, String requirements="requirements"){
        super.execOut("pip${pythonVersion} install -r ${requirements} -t .")
    }
}
