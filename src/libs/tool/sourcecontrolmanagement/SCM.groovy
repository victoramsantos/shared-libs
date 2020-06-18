package libs.tool.sourcecontrolmanagement

interface SCM {
    String cloneAndCheckout(String repository, String applicationName, String branch)
}