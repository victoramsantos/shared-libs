package libs.utils

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
}
