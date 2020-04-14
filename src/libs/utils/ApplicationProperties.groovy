package libs.utils

class ApplicationProperties {
    private Map properties
    ApplicationProperties(Map params){
        this.properties = params
        print(this.properties["PIPELINE_CI"])
        print(this.properties.get("PIPELINE_CI"))
    }

    String getString(String key)  {
        printMap(this.properties)
        return this.properties[key]
    }

    static void printMap(Map map){
        for(Map.Entry entry: map.entrySet()){
            print("${entry.key}--${entry.value}")
        }
    }
}
