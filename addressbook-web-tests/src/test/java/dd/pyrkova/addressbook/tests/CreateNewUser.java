package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.*;
import org.testng.annotations.*;

public class CreateNewUser extends TestBase {

  @Test
  public void testCreateNewUser() throws Exception {

    app.getUserHelper().gotoNewUserPage();
    app.getUserHelper().fillInUserData(new UserData("Daria", "Vladimirovna", "Pyrkova", "dd", "d@u.ru", "d@g.com", "999", "777", "888", "1", "January", "1990"));
    app.getUserHelper().fillInCompany("U");
    app.getUserHelper().fillInAddress("Dolgoprudny");
    app.getUserHelper().submitUserCreation();
    app.getNavigationHelper().returnToHomePage();
    app.getSessionHelper().logout();
  }

}
