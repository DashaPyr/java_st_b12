package dd.pyrkova.rest;

import dd.pyrkova.rest.appmeneger.Issue;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.apache.http.client.fluent.Request.Get;
import static org.apache.http.client.fluent.Request.Post;
import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue issue = new Issue().withSubject("Test subject").withDescription("Test description");
    int id = createIssue(issue);
    Set<Issue> newIssues = getIssues();
//    oldIssues.add(issue.withId(id));
    assertEquals(newIssues.size(), oldIssues.size() + 1);
    oldIssues.add(issue.withId(id).withStateName("Open"));
//    assertEquals(oldIssues, newIssues);
  }






}
