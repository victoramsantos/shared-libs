package libs.utils

class Utils {
    static Map parseKeyValueStringToMap(String keyValueString, String delimiterLine = "\n", String delimiter = "="){
        Map map = new HashMap()
        String[] lines = keyValueString.split(delimiterLine)

        for(String line: lines){
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
