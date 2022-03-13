/**
 *A scripted field that calculates a value based on issue fields
 */
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