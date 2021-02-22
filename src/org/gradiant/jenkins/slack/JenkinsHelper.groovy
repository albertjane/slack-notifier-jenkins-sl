package org.gradiant.jenkins.slack


String getBranchName() {
    if (env.BRANCH_NAME != null) return env.BRANCH_NAME
    return env.GIT_BRANCH
}

boolean isMultibranch() {
    return getBranchName() != null
}


int getBuildNumber() {
    return currentBuild.number
}


String getAbsoluteUrl() {
    return currentBuild.absoluteUrl
}

String getBlueUrl() {
    def buildURL = env.BUILD_URL
    def absoluteBlueUrl = buildURL.replace("job/${env.JOB_NAME}", "blue/organizations/jenkins/${env.JOB_NAME}/detail/${env.JOB_NAME}")
    return absoluteBlueUrl
}

String getProjectName() {
    if(isMultibranch()) return getMultiBranchProjectName()

    return env.JOB_NAME
}

String getMultiBranchProjectName() {
    String[] entries = env.JOB_NAME.split('/')
    def len = entries.length

    if (len == 1 || len == 2) return entries[0]

    entries = entries.take(len - 1)
    return entries.join('/')
}


List<String> getChanges() {
    List<String> messages = []
    for (int i = 0; i < currentBuild.changeSets.size(); i++) {
        def entries = currentBuild.changeSets[i].items
        for (int j = 0; j < entries.length; j++) {
            def entry = entries[j]
            messages.add("\t- ${entry.msg} [${entry.author}]")
        }
    }

    return messages
}

String getDuration() {
    return currentBuild.durationString.replace(' and counting', '')
}


String getCurrentStatus() {
    return currentBuild.currentResult
}


String getPreviousStatus() {
    def prev = currentBuild.previousBuild?.currentResult

    if (!prev) {
        return 'SUCCESS'
    }

    return prev
}
