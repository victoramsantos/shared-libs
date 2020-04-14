package libs.tool.cloudprovider

import static pipeline.context.PipelineContext.shell

class Aws implements CloudProvider{
    private String bucket = "victoramsantos-ci-cd"

    @Override
    void upload(String filePath, String destinationFileName, String appName){
        shell("aws s3 cp ${filePath} s3://${this.bucket}/${appName}/${destinationFileName}")
    }

    @Override
    void download(String fileName, String destinationPath, String appName){
        shell("aws s3 cp s3://${this.bucket}/${appName}/${fileName} ${destinationPath}")
    }
}
