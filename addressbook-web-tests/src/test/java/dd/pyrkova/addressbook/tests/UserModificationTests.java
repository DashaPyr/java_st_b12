package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.TestBase;
import dd.pyrkova.addressbook.model.UserData;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.management.MBeanRegistration;
import java.util.Comparator;
import java.util.HashSet;
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
    app.getUserHelper().initUserModification(before.size() - 1);
    UserData user = new UserData(before.get(before.size() - 1).getId(),"Darya", "V.", "Pyrkova", "ddd", "M", "Moscow region", "d@m.ru", "d@g.com", "555", "777", "333", "1", "January", "1990", null);
    app.getUserHelper().fillInUserData(user, false);
    app.getUserHelper().submitUserModification();
    app.getNavigationHelper().returnToHomePage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(user);
    Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
