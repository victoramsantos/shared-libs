package libs.build


import static pipeline.context.PipelineContext.shell

class Pip {
    void install(String requirements){
        shell("pip install -r $requirements -t . --no-cache-dir")
    }

    void installDependency(String dependency){
        shell("pip install $dependency -t . --no-cache-dir")
    }
}
