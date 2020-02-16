package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.*;
import org.testng.annotations.*;

public class CreateNewUser extends TestBase {

  @Test
  public void testCreateNewUser() throws Exception {

    app.getUserHelper().gotoNewUserPage();
    app.getUserHelper().fillInUserName(new UserNameData("Daria", "Vladimirovna", "Pyrkova", "dd"));
    app.getUserHelper().fillInCompany("U");
    app.getUserHelper().fillInAddress("Dolgoprudny");
    app.getUserHelper().fillInPhones(new PhoneData("999", "777", "888"));
    app.getUserHelper().fillInEmail(new EmailData("d@u.ru", "d@g.com"));
    app.getUserHelper().fillInBirthday(new BirthData("1", "January", "1990"));
    app.getUserHelper().submitUserCreation();
    app.getNavigationHelper().returnToHomePage();
    app.getSessionHelper().logout();
  }

}
