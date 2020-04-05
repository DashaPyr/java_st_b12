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

/*  @BeforeClass
  public void init(){
    RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
  }*/

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue issue = new Issue().withSubject("Test subject").withDescription("Test description");
    int id = createIssue(issue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(issue.withId(id));
    assertEquals(oldIssues, newIssues);
  }






}
