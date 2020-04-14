package libs.docker

interface Docker {
    void createRepository(String repository, String applicationName)
    void build(String repository, String applicationName, String tag)
    void push(String repository, String applicationName, String tag)
}
