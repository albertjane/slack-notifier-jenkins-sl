package org.gradiant.jenkins.slack


String format(String title = '', String message = '', String testSummary = '') {
    def helper = new JenkinsHelper()

    def project = helper.getProjectName()
    def branchName = helper.getBranchName()
    def buildNumber = helper.getBuildNumber()
    def url = helper.getAbsoluteUrl()
    def urlBlue = helper.getBlueUrl()

    def result = "${project}"

    if (branchName != null) result = "${result} » ${branchName}"

    result = "${result} - #${buildNumber} ${title.trim()} (<${url}|Open>) (<${urlBlue}|BlueOcen>)"
    if (message) result = result + "\nChanges:\n\t ${message.trim()}"
    if (testSummary) result = result + "\n ${testSummary}"

    return result
}
