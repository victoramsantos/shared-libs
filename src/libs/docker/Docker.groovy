package libs.docker

interface Docker {
    void login(String region)
    void build(String repository, String applicationName, String tag)
    void push(String repository, String applicationName, String tag)
}
