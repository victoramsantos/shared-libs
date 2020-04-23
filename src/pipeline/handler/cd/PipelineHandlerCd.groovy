package pipeline.handler.cd

interface PipelineHandlerCd {
    void scmClone()
    void creatingService()
    void applyingDeployment()
}