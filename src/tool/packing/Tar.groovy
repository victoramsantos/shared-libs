package tool.packing

import utils.PipelineHandler

class Tar extends PipelineHandler {

    Tar(Object steps) {
        super(steps)
    }

    void packing(String appName, String buildNumber){
        super.exec("tar -zcvf ${appName}-${buildNumber}.gz ${appName}/")
    }
}
