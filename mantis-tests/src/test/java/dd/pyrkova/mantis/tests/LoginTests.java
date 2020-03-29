package dd.pyrkova.mantis.tests;

import dd.pyrkova.mantis.appmanager.HttpSession;
import dd.pyrkova.mantis.model.TestBase;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator"));
    assertTrue(session.isLoggedInAs("administrator"));
  }
}