package org.gradiant.jenkins.slack


String format(String title = '', String message = '', String testSummary = '') {
    def helper = new JenkinsHelper()

    def project = helper.getProjectName()
    def branchName = helper.getBranchName()
    def buildNumber = helper.getBuildNumber()
    def url = helper.getAbsoluteUrl()
    def urlBlueResult = helper.getBlueResultUrl()
    def urlBlueChanges = helper.getBlueChangesUrl()

    def result = "${project}"

    if (branchName != null) result = "${result} » ${branchName}"

    result = "${result} - #${buildNumber} ${title.trim()} (<${urlBlue}|Build Results>) - (<${url}|Classic>)"
    if (message) result = result + "\n<${urlBlueChanges}|Changes>:\n\t ${message.trim()}"
    if (testSummary) result = result + "\n ${testSummary}"

    return result
}
