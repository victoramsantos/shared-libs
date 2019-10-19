package tool.cloudprovider

interface CloudProvider {
    void upload(String filePath, String destinationFileName, String appName)
    void download(String fileName, String destinationPath, String appName)
}