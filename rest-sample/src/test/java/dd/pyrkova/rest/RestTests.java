package dd.pyrkova.rest;

import com.jayway.restassured.RestAssured;
import dd.pyrkova.rest.appmeneger.Issue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.apache.http.client.fluent.Request.Get;
import static org.apache.http.client.fluent.Request.Post;
import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{

  @Test
  public void testCreateIssue() throws IOException {
    //skipIfNotFixed(2673); // Bug status - Open
    //skipIfNotFixed(608); // Bug status - Closed
  //  skipIfNotFixed(768); // Bug status - Resolved
    //skipIfNotFixed(1); // Bug status - Deleted

    Set<Issue> oldIssues = getIssues();
    Issue issue = new Issue().withSubject("Test subject").withDescription("Test description");
    int id = createIssue(issue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(issue.withId(id));
    assertEquals(oldIssues, newIssues);
  }






}
