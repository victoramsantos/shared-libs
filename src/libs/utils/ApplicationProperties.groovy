package libs.utils

class ApplicationProperties {
    private Map properties
    ApplicationProperties(Map params){
        this.properties = params
    }

    String getString(String key) {
        return this.properties[key]
    }
}
