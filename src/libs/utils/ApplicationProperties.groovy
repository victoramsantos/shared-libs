package libs.utils

class ApplicationProperties {
    private Map properties = [:]
    ApplicationProperties(def params){
        this.properties += params
    }

    String getString(String key)  {
        printMap(this.properties)
        return this.properties.get(key) as String
    }

    static void printMap(Map map){
        for(Map.Entry entry: map.entrySet()){
            print("${entry.key}--${entry.value}")
        }
    }
}
