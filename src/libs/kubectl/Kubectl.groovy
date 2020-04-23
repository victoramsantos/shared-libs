package libs.kubectl


import static pipeline.context.PipelineContext.shell

class Kubectl {
    void applyService(String filePath) {
        shell(
                "kubectl apply -f $filePath"
        )
    }

    void applyDeployment(String filePath) {
        shell(
                "kubectl apply -f $filePath"
        )
    }
}
