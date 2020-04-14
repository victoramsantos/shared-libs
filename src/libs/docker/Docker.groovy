package libs.docker

interface Docker {
    void login(String region)
    void tag()
    void build()
}
