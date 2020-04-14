package libs.build


import static pipeline.context.PipelineContext.shell

class Pip {
    void install(String pythonVersion, String requirements){
        shell("pip $pythonVersion install -r $requirements -t . --no-cache-dir")
    }

    void installDependency(String pythonVersion, String dependency){
        shell("pip${pythonVersion} install ${dependency} -t . --no-cache-dir")
    }
}
