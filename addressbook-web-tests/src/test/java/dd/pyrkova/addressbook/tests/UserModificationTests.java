package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.TestBase;
import dd.pyrkova.addressbook.model.UserData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UserModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().returnToHomePage();
    if (! app.getUserHelper().isThereAUser()){
      app.getUserHelper().createUser(new UserData("Daria", "Vladimirovna", "Pyrkova", "dd", "U", "Dolgoprudny", "d@u.ru", "d@g.com", "999", "777", "888", "1", "January", "1990", "[none]"), true);
      app.getNavigationHelper().returnToHomePage();
    }
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().selectUser(before.size() - 1);
    app.getUserHelper().initUserModification();
    app.getUserHelper().fillInUserData(new UserData("Darya", "V.", "Pyrkova", "ddd", "M", "Moscow region", "d@m.ru", "d@g.com", "555", "777", "333", "1", "January", "1990", null), false);
    app.getUserHelper().submitUserModification();
    app.getNavigationHelper().returnToHomePage();
//    app.getSessionHelper().logout();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size());
  }
}
