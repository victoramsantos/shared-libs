package libs.utils

class ApplicationProperties {
    private Map applicationProperties

    ApplicationProperties(Map... maps){
        this.applicationProperties = new HashMap()
        maps.each { map ->
            this.applicationProperties += map
        }
    }

    String getString(String key) {
        return this.applicationProperties.get(key)
    }

    void add(String key, String value) {
        this.applicationProperties[key] = value
    }

    Map getApplicationProperties(){
        return this.applicationProperties
    }
}
