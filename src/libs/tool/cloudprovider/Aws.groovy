package libs.tool.cloudprovider

import libs.handler.PipelineHandler

class Aws extends PipelineHandler implements CloudProvider{
    private String bucket = "victoramsantos-ci-cd"

    Aws(Object pipelineContext) {
        super(pipelineContext)
    }

    @Override
    void upload(String filePath, String destinationFileName, String appName){
        super.exec("aws s3 cp ${filePath} s3://${this.bucket}/${appName}/${destinationFileName}")
    }

    @Override
    void download(String fileName, String destinationPath, String appName){
        super.exec("aws s3 cp s3://${this.bucket}/${appName}/${fileName} ${destinationPath}")
    }
}
