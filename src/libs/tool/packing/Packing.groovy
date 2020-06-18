package libs.tool.packing

interface Packing {
    String packing(String appName, String buildNumber)
    String unpacking(String fileName)
}