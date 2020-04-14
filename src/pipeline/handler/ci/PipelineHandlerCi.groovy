package pipeline.handler.ci

interface PipelineHandlerCi {
    void scmClone()
    String getBuildImage()
    void buildAndTest()
    void buildDockerImage()
}