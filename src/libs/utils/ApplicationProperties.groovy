package libs.utils

class ApplicationProperties {
    private Map properties
    ApplicationProperties(Map params){
        this.properties = params
        print(this.properties["PIPELINE_CI"])
        print(this.properties.get("PIPELINE_CI"))
    }

    String getString(String key) {
        return this.properties[key]
    }
}
