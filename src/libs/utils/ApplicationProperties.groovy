package libs.utils

class ApplicationProperties {
    private Map applicationProperties

    ApplicationProperties(Map applicationProperties){
        this.applicationProperties = applicationProperties
    }

    String getString(String key) {
        return this.applicationProperties.get(key)
    }

    void add(String key, String value) {
        this.applicationProperties[key] = value
    }
}
