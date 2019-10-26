def call(Map params) {

    params.each{entry -> println "$entry.key: $entry.value"}

    pipeline {
        agent any
        options {
            skipStagesAfterUnstable()
        }
        stages {
            stage("Cloning") {
                steps {
                    script {
                       echo params.get("VAL1")
                       echo params.get("VAL2")
                    }
                }
            }
        }
    }
}