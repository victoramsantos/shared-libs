package libs.utils

class ApplicationProperties {
    private Map applicationProperties

    ApplicationProperties(String defaultProperties, Map applicationProperties){
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

    static Map parseKeyValueStringToMap(String keyValueString, String delimiterLine = "\n", String delimiter = "="){
        Map map = new HashMap()
        String[] lines = keyValueString.split(delimiterLine)

        for(String line: lines){
            String[] keyValue = line.split(delimiter)
            map.put(keyValue[0], keyValue[1])
        }

        return map
    }
}
