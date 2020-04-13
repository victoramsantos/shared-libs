package libs.tool.packing

import libs.handler.PipelineHandler

class Tar extends PipelineHandler implements Packing{

    Tar(Object pipelineContext) {
        super(pipelineContext)
    }

    @Override
    String packing(String appName, String buildNumber){
        super.exec("tar -zcvf ${appName}-${buildNumber}.gz ${appName}/")
        return "${appName}-${buildNumber}.gz"
    }

    @Override
    String unpacking(String fileName) {
        return null
    }
}
