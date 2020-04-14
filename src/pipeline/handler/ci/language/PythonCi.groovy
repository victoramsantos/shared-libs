package pipeline.handler.ci.language

import libs.utils.ApplicationProperties
import pipeline.handler.ci.GenericCi

class PythonCi extends GenericCi {
    PythonCi(ApplicationProperties properties) {
        super(properties)
    }
}
