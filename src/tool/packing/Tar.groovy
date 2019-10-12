package tool.packing

import handler.PipelineHandler

class Tar extends PipelineHandler {

    Tar(Object pipelineContext) {
        super(pipelineContext)
    }

    void packing(String appName, String buildNumber){
        super.exec("tar -zcvf ${appName}-${buildNumber}.gz ${appName}/")
        return ${appName}-${buildNumber}.gz
    }
}
