package dd.pyrkova.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import dd.pyrkova.rest.appmeneger.ApplicationManager;
import dd.pyrkova.rest.appmeneger.Issue;
import org.apache.http.client.fluent.Executor;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.Set;

import static org.apache.http.client.fluent.Request.Get;
import static org.apache.http.client.fluent.Request.Post;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));

  int createIssue(Issue issue) throws IOException {
    String json = getExecutor()
            .execute(
                    Post("https://bugify.stqa.ru/api/issues.json")
                            .bodyForm(new BasicNameValuePair("subject", issue.getSubject())
                                    , new BasicNameValuePair("description", issue.getDescription())))
            .returnContent().asString();

/*    String json = RestAssured.given()
            .parameter("subject", issue.getSubject()).parameter("description", issue.getDescription())
            .post("https://bugify.stqa.ru/api/issues.json").asString();*/
    JsonElement jsonParsed = JsonParser.parseString(json);
    return jsonParsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Get("https://bugify.stqa.ru/api/issues.json?limit=500")).returnContent().asString();

//    String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json?limit=500").asString();
    JsonElement jsonParsed = JsonParser.parseString(json);
    JsonElement jsonIssues = jsonParsed.getAsJsonObject().get("issues");
    Set<Issue> issues = new Gson().fromJson(jsonIssues, new TypeToken<Set<Issue>>() {}.getType());
    return issues;
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

/*  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }*/

}