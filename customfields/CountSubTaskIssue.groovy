/**
 * A scripted field that can count the number of sub-tasks in an issue
 */

// check if issue not sub-task type, then return sub-task in issue
if (!issue.getIssueType().isSubTask()) {
    return issue.getSubTaskObjects().size()
}