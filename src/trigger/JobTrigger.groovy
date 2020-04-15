package trigger

import libs.utils.ApplicationProperties
import libs.utils.Utils

class JobTrigger {
    static void trigger(def context, ApplicationProperties applicationProperties) {
        String job = selectJob(applicationProperties)
        String jsonMap = Utils.parseMapToJsonMap(applicationProperties.properties)

        context.build(
                job: job,
                parameters: [
                        [$class: 'StringParameterValue', name: 'jsonMap', value: jsonMap]
                ]
        )
    }

    private static String selectJob(ApplicationProperties applicationProperties){
        return applicationProperties.getString("GENERIC_CD_JOB")
    }
}
