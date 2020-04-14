package pipeline.handler.ci

import libs.utils.ApplicationProperties
import pipeline.handler.ci.language.PythonCi

class PipelineHandlerCiFactory {

    static PipelineHandlerCi build(ApplicationProperties properties){
        PipelineHandlerCi pipelineHandlerCi
        CiTypes ciTypes = CiTypes.valueOf(properties.getString("PIPELINE_CI"))

        switch (ciTypes){
            case CiTypes.PYTHON:
                pipelineHandlerCi = new PythonCi(properties)
                break
        }

        return pipelineHandlerCi
    }

}
