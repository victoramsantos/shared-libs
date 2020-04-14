package pipeline.handler.ci

interface PipelineHandlerCi {
    void scmClone(String repository, String applicationName, String branch)
    String getBuildImage(String runtimeVersion)
    void buildAndTest(String runtimeVersion, String requirementsPath)
    void codeQualityAnalysis()
    void buildImage()
}