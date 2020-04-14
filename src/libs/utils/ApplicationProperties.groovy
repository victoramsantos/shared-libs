package libs.utils

class ApplicationProperties {
    private Map properties

    void init(Map properties){
        this.properties = properties
    }

    String getString(String key) {
        return this.properties.get(key)
    }
}
