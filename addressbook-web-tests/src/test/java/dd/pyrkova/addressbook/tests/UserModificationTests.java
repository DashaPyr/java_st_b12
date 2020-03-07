package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.TestBase;
import dd.pyrkova.addressbook.model.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void preconditions (){
    app.goTo().homePage();
    if (app.user().list().size() == 0){
      app.user().create(new UserData("Daria", "Vladimirovna", "Pyrkova", "dd", "U", "Dolgoprudny", "d@u.ru", "d@g.com", "999", "777", "888", "1", "January", "1990", "[none]"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testGroupModification() {
    List<UserData> before = app.user().list();
    int index = before.size() - 1;
    UserData user = new UserData(before.get(index).getId(),"Darya", "V.", "Pyrkova", "ddd", "M", "Moscow region", "d@m.ru", "d@g.com", "555", "777", "333", "1", "January", "1990", null);
    app.user().modify(index, user);
    app.goTo().homePage();
    List<UserData> after = app.user().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(user);
    Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
