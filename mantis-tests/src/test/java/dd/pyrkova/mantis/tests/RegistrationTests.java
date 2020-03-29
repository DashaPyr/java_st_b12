package dd.pyrkova.mantis.tests;

import dd.pyrkova.mantis.model.TestBase;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration() {
    app.registration().start("user1", "user1@localhost.localdomain");
  }
}
