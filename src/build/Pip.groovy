package build

class Pip {
    void install(String pythonVersion, String requirements){
        sh "pip${pythonVersion} install -r ${requirements} -t ."
    }
}
