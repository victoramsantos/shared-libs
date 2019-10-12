package tool.aws

import handler.PipelineHandler

class Aws extends PipelineHandler{
    private String bucket = "victoramsantos-ci-cd"

    Aws(Object pipelineContext) {
        super(pipelineContext)
    }

    void uploadToS3(String filePath, String destinationFileName, String appName){
        super.exec("aws s3 cp ${filePath} s3://${this.bucket}/${appName}/${destinationFileName}")
    }

    void downloadFromS3(String fileName, String destinationPath, String appName){
        super.exec("aws s3 cp s3://${this.bucket}/${appName}/${fileName} ${destinationPath}")
    }
}
