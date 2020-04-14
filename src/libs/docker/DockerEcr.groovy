package libs.docker

import static pipeline.context.PipelineContext.shell

class DockerEcr implements Docker {

    DockerEcr(String region){
        shell("\$(aws ecr get-login --region $region --no-include-email)")
    }

    @Override
    void createRepository(String repository, String applicationName) {
        shell("aws ecr describe-repositories --repository-names $repository/$applicationName" +
                " || aws ecr create-repository --repository-name $repository/$applicationName")
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
