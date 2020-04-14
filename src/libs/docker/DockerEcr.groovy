package libs.docker

import static pipeline.context.PipelineContext.shell

class DockerEcr implements Docker {
    @Override
    void login(String region){
        shell("\$(aws ecr get-login --region $region --no-include-email)")
    }

    @Override
    void tag() {

    }

    @Override
    void build(){

    }
}
