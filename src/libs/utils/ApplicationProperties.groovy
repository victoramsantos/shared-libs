package libs.utils

class ApplicationProperties {
    private Map properties
    ApplicationProperties(Map params){
        this.properties = new HashMap(params)
    }

    String getString(String key)  {
        return this.properties.get(key) as String
    }

}
