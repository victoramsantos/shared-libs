package libs.docker

import static pipeline.context.PipelineContext.shell

class DockerEcr implements Docker {
    @Override
    void login(String region){
        shell("\$(aws ecr get-login --region $region --no-include-email)")
    }

    @Override
    void build(String repository, String applicationName, String tag){
        shell("docker build -t $repository/$applicationName:$tag . ")
    }

    @Override
    void push(String repository, String applicationName, String tag) {
        shell("docker push $repository/$applicationName:$tag")
    }
}
