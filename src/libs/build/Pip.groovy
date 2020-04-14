package libs.build


import static pipeline.context.PipelineContext.shell

class Pip {
    void install(String requirements){
        shell("pip install -r $requirements")
    }

    void installDependency(String dependency){
        shell("pip install $dependency")
    }
}
