package dd.pyrkova.rest;

import dd.pyrkova.rest.Issue;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.message.BasicNameValuePair;
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
    oldIssues.add(issue.withId(id));
    assertEquals(oldIssues, newIssues);
  }






}
