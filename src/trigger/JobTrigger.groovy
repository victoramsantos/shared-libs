package trigger

import libs.utils.ApplicationProperties
import libs.utils.Utils

class JobTrigger {
    static void trigger(def context, ApplicationProperties applicationProperties) {
        String job = ""
        String jsonMap = Utils.parseMapToJson(applicationProperties.properties)

        context.build(
                job: "${}",
                parameters: [
                        [$class: 'StringParameterValue', name: 'jsonMap', value: jsonMap]
                ]
        )
    }

    private static String selectJob(){
        return ""
    }
}
