def call(Map jobParams) {

    print(getClass().protectionDomain.codeSource.location.path)

    Properties props = new Properties()
    String path = libraryResource 'default.properties'
    print(path)
    File propsFile = new File(path)
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