package libs.utils

class ApplicationProperties {
    private Map properties
    ApplicationProperties(Map params){
        this.properties = new HashMap(params)
        printMap(this.properties)
    }

    String getString(String key)  {
        return this.properties.get(key) as String
    }

    static void printMap(Map map){
        for(Map.Entry entry: map.entrySet()){
            print("${entry.key}--${entry.value}")
        }
    }
}
