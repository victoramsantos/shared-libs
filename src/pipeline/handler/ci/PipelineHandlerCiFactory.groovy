package pipeline.handler.ci

import libs.utils.ApplicationProperties
import pipeline.handler.ci.language.PythonCi

class PipelineHandlerCiFactory {

    static PipelineHandlerCi build(ApplicationProperties applicationProperties){
        PipelineHandlerCi pipelineHandlerCi
        CiTypes ciTypes = CiTypes.valueOf(applicationProperties.getString("PIPELINE_CI"))

        switch (ciTypes){
            case CiTypes.PYTHON:
                pipelineHandlerCi = new PythonCi(applicationProperties)
                break
        }

        return pipelineHandlerCi
    }
}
