package libs.utils

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

class Utils {
    static Map parseKeyValueStringToMap(String keyValueString, String delimiterLine = "\n", String delimiter = "="){
        Map map = new HashMap()
        keyValueString.split(delimiterLine).each { line ->
            String[] keyValue = line.split(delimiter)
            map.put(keyValue[0], keyValue[1])
        }

        return map
    }

    static Map parsePropertyFile(def context, String fileName){
        return parseKeyValueStringToMap(
                context.libraryResource(fileName) as String
        )
    }

    static parseMapToJson(Map map){
        return JsonOutput.toJson(map)
    }

    static parseJsonToMap(String json){
        JsonSlurper jsonSlurper = new JsonSlurper()
        return jsonSlurper.parseText(json)
    }
}
