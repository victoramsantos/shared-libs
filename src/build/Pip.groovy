package build

import utils.Sh

class Pip extends Sh{
    void install(String pythonVersion, String requirements="requirements"){
        super.execOut("pip${pythonVersion} install -r ${requirements} -t .")
    }
}
