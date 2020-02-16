package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.EmailData;
import dd.pyrkova.addressbook.model.PhoneData;
import dd.pyrkova.addressbook.model.TestBase;
import dd.pyrkova.addressbook.model.UserNameData;
import org.testng.annotations.Test;

public class UserModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().returnToHomePage();
    app.getSelectHelper().selectElement();
    app.getUserHelper().initUserModification();
    app.getUserHelper().fillInUserName(new UserNameData("Darya", "V.", "Pyrkova", "ddd"));
    app.getUserHelper().fillInAddress("Moscow region");
    app.getUserHelper().fillInPhones(new PhoneData("555", "777", "333"));
    app.getUserHelper().fillInEmail(new EmailData("d@m.ru", "d@g.com"));
    app.getUserHelper().submitUserModification();
    app.getNavigationHelper().returnToHomePage();
    app.getSessionHelper().logout();
  }
}
