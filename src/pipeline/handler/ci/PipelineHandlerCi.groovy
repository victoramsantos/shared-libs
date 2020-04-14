package pipeline.handler.ci

interface PipelineHandlerCi {
    void scmClone(String repository, String applicationName, String branch);
    void buildAndTest();
    void codeQualityAnalysis();
    void buildImage();
}