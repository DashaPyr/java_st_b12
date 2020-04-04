package dd.pyrkova.mantis.tests;

import dd.pyrkova.mantis.appmanager.HttpSession;
import dd.pyrkova.mantis.model.MailMessage;
import dd.pyrkova.mantis.model.TestBase;
import dd.pyrkova.mantis.model.UserData;
import dd.pyrkova.mantis.model.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

  private UserData user;

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException, SQLException {
    Users users = app.dbHelper().userInfoFromDb();
    for (UserData userFind : users) {
      if (!userFind.getName().equals("administrator")) {
        user = userFind;
        break;
      }
    }

    String passwordNew = "1new2password";
    String email = user.getEmail();
    String name = user.getName();
    String passwordAdmin = "root";
    String loginAdmin = "administrator";

    app.registration().login(loginAdmin, passwordAdmin);
    app.registration().chooseUser(name);
    app.registration().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 30000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, passwordNew);
    assertTrue(app.newSession().login(name, passwordNew));

    HttpSession session = app.newSession();
    assertTrue(session.login(name, passwordNew));
    assertTrue(session.isLoggedInAs(name));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }

}
