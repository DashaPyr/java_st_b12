package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.TestBase;
import dd.pyrkova.addressbook.model.UserData;
import org.testng.annotations.Test;

public class UserModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().returnToHomePage();
    app.getSelectHelper().selectElement();
    app.getUserHelper().initUserModification();
    app.getUserHelper().fillInUserData(new UserData("Darya", "V.", "Pyrkova", "ddd", "M", "Moscow region", "d@m.ru", "d@g.com", "555", "777", "333", "1", "January", "1990", null), false);
    app.getUserHelper().submitUserModification();
    app.getNavigationHelper().returnToHomePage();
//    app.getSessionHelper().logout();
  }
}
