package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.*;
import org.testng.annotations.*;

public class CreateNewUser extends TestBase {

  @Test
  public void testCreateNewUser() throws Exception {

    app.gotoNewUserPage();
    app.fillInUserName(new UserNameData("Daria", "Vladimirovna", "Pyrkova", "dd"));
    app.fillInCompany("U");
    app.fillInAddress("Dolgoprudny");
    app.fillInPhones(new PhoneData("999", "777", "888"));
    app.fillInEmail(new EmailData("d@u.ru", "d@g.com"));
    app.fillInBirthday(new BirthData("1", "January", "1990"));
    app.submitUserCreation();
    app.returnToHomePage();
    app.logout();
  }

}
