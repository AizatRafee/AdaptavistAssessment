import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.bc.issue.search.SearchService
import com.atlassian.jira.jql.parser.JqlQueryParser
import com.atlassian.jira.web.bean.PagerFilter

if (issue.isSubTask()) {
    // get component
    def jqlQueryParser = ComponentAccessor.getComponent(JqlQueryParser)
    def searchService = ComponentAccessor.getComponent(SearchService)

    def user = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser()

    // edit this query to suit
    def query = jqlQueryParser.parseQuery("issueFunction in parentsOf('project = project1 AND issue = ${issue.getKey()}')")

    def search = searchService.search(user, query, PagerFilter.getUnlimitedFilter())

    return search.getResults()
}