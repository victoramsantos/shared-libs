package pipeline.handler.ci

import libs.utils.ApplicationProperties
import libs.utils.Log
import pipeline.handler.ci.language.PythonCi

class PipelineHandlerCiFactory {

    static PipelineHandlerCi build(ApplicationProperties properties){
        PipelineHandlerCi pipelineHandlerCi
        Log.info(properties.getString("PIPELINE_CI"))
        CiTypes ciTypes = CiTypes.valueOf(properties.getString("PIPELINE_CI"))

        switch (ciTypes){
            case CiTypes.PYTHON:
                pipelineHandlerCi = new PythonCi(properties)
                break
        }

        return pipelineHandlerCi
    }

}
