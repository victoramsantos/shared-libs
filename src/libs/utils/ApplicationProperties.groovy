package libs.utils

class ApplicationProperties {
    private Map applicationProperties

    ApplicationProperties(Map defaultProperties, Map applicationProperties){
        this.applicationProperties = defaultProperties + applicationProperties
    }

    String getString(String key) {
        return this.applicationProperties.get(key)
    }

    void add(String key, String value) {
        this.applicationProperties[key] = value
    }

    Map testing(){
        return this.applicationProperties
    }
}
