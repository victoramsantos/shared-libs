def call(Map jobParams) {

    print(getClass().protectionDomain.codeSource.location.path)

    Properties props = new Properties()
    File propsFile = new File(getClass().protectionDomain.codeSource.location.path+'../resources/default.properties')
    props.load(propsFile.newDataInputStream())
    print(props)
//    ApplicationProperties applicationProperties = new ApplicationProperties(defaultProperties, jobParams)

    pipeline {
        agent any
        stages {
            stage("works") {
                steps {
                    script {
                        for (Map.Entry entry : props.entrySet()) {
                            print("${entry.key} == ${entry.value}")
                        }
                    }
                }
            }
        }
        post {
            always {
                cleanWs()
            }
        }
    }
}