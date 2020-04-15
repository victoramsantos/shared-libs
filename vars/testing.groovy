import libs.utils.ApplicationProperties

def call(Map jobParams) {
    print(System.getProperty("user.dir"))
    print(getClass().protectionDomain.codeSource.location.path)
    print(new File(".").getAbsolutePath())

    Properties props = new Properties()
    File propsFile = new File('resources/default.properties')
    props.load(propsFile.newDataInputStream())
    print(props)
    ApplicationProperties applicationProperties = new ApplicationProperties(defaultProperties, jobParams)

    pipeline {
        agent any
        stages {
            stage("works") {
                steps {
                    script {
                        for (Map.Entry entry : applicationProperties.testing()) {
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