package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class CreateNewUser extends TestBase {

  @Test
  public void testCreateNewUser() throws Exception {
    app.getNavigationHelper().returnToHomePage();
    int before = app.getUserHelper().getUserCount();
    app.getUserHelper().createUser(new UserData("Daria", "Vladimirovna", "Pyrkova", "dd", "U", "Dolgoprudny", "d@u.ru", "d@g.com", "999", "777", "888", "1", "January", "1990", "[none]"), true);
    app.getNavigationHelper().returnToHomePage();
 //   app.getSessionHelper().logout();
    int after = app.getUserHelper().getUserCount();
    Assert.assertEquals(after, before+1);
  }

}
