# AdaptavistAssessment
This repo for Adaptavist assessment code

## Task 1
# 1.1 ScriptRunner & Jira

> A scripted field that calculates a value based on issue fields; (Tip: Create two number fields)

```
import com.atlassian.jira.component.ComponentAccessor

def customFieldManager = ComponentAccessor.getCustomFieldManager()

// get 2 custom fields
def cField1 = customFieldManager.getCustomFieldObject(10114)
def cField2 = customFieldManager.getCustomFieldObject(10115)

// get value from this 2 custom fields
def cFieldValue1 = issue.getCustomFieldValue(cField1)
def cFieldValue2 = issue.getCustomFieldValue(cField2)

// check if value is number, then sum with count
double count = 0
if (cFieldValue1 instanceof Number) {
    count += (Number) cFieldValue1
}
if (cFieldValue2 instanceof Number) {
    count += (Number) cFieldValue2
}

return count
```


> A scripted field that can count the number of sub-tasks in an issue

```
// check if issue not sub-task type, then return sub-task in issue
if (!issue.getIssueType().isSubTask()) {
    return issue.getSubTaskObjects().size()
}
```

> A custom listener script that updates the issue description with the issue last comment

```
import com.atlassian.jira.component.ComponentAccessor

// get component
def user = ComponentAccessor.jiraAuthenticationContext.getLoggedInUser()
def issueService = ComponentAccessor.getIssueService()

// get event comment
def comment = event.getComment().getBody()

// update description with comment
def issueInput = issueService.newIssueInputParameters().setDescription(comment)

def updateValidationResult = issueService.validateUpdate(user, event.issue.id, issueInput)

assert updateValidationResult.isValid() : updateValidationResult.getErrorCollection()

def updateResult = issueService.update(user, updateValidationResult)

assert updateResult.isValid() : updateResult.getErrorCollection()
```
