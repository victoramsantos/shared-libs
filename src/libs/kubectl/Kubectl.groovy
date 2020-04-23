package libs.kubectl


import static pipeline.context.PipelineContext.shell

class Kubectl {
    void applyService(String filePath) {
        shell(
                "kubectl apply service -f $filePath"
        )
    }

    void applyDeployment(String filePath) {
        shell(
                "kubectl apply deployment -f $filePath"
        )
    }
}
