package dd.pyrkova.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import dd.pyrkova.rest.appmeneger.ApplicationManager;
import dd.pyrkova.rest.appmeneger.Issue;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));

  public int createIssue(Issue issue) throws IOException {
    int issueId = 1111;
    skipIfNotFixed(issueId);
    System.out.println("Create new issue because ID " + issueId + " is fixed");

    String json = getExecutor()
            .execute(
                    Request.Post("https://bugify.stqa.ru/api/issues.json")
                            .bodyForm(new BasicNameValuePair("subject", issue.getSubject())
                                    , new BasicNameValuePair("description", issue.getDescription())))
            .returnContent().asString();
    JsonElement jsonParsed = JsonParser.parseString(json);
    return jsonParsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json?limit=500")).returnContent().asString();
    JsonElement jsonIssues = new JsonParser().parse(json).getAsJsonObject().get("issues");
    Set<Issue> issues = new Gson().fromJson(jsonIssues, new TypeToken<Set<Issue>>() {}.getType());
    return issues;
  }

  boolean isIssueOpen(int issueId) throws IOException {
    String json = getExecutor()
            .execute(Request.Get("https://bugify.stqa.ru/api/issues/" + issueId + ".json"))
            .returnContent().asString();  //ссылка
    JsonElement jsonIssues = new JsonParser().parse(json).getAsJsonObject().get("issues").getAsJsonArray().get(0);
    String state = jsonIssues.getAsJsonObject().get("state_name").getAsString();
    System.out.println(issueId + " is " + state);
    if (state.equals("Resolved")) {
      return true;
    } else {
      return false;
    }
  }

  public Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}