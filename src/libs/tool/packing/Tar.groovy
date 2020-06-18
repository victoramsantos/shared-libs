package libs.tool.packing

import static pipeline.context.PipelineContext.shell


class Tar implements Packing{

    @Override
    String packing(String appName, String buildNumber){
        shell("tar -zcvf ${appName}-${buildNumber}.gz ${appName}/")
        return "${appName}-${buildNumber}.gz"
    }

    @Override
    String unpacking(String fileName) {
        return null
    }
}
