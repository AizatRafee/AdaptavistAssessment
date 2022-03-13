/**
 * A custom listener script that updates the issue description with the issue last comment
 */
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