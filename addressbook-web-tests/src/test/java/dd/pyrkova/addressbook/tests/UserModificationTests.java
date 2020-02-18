package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.EmailData;
import dd.pyrkova.addressbook.model.PhoneData;
import dd.pyrkova.addressbook.model.TestBase;
import dd.pyrkova.addressbook.model.UserData;
import org.testng.annotations.Test;

public class UserModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().returnToHomePage();
    app.getSelectHelper().selectElement();
    app.getUserHelper().initUserModification();
    app.getUserHelper().fillInUserName(new UserData("Darya", "V.", "Pyrkova", "ddd", "d@m.ru", "d@g.com"));
    app.getUserHelper().fillInAddress("Moscow region");
    app.getUserHelper().fillInPhones(new PhoneData("555", "777", "333"));
    app.getUserHelper().submitUserModification();
    app.getNavigationHelper().returnToHomePage();
    app.getSessionHelper().logout();
  }
}
