package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class CreateNewUser extends TestBase {

  @Test
  public void testCreateNewUser() throws Exception {
    app.goTo().homePage();
    List<UserData> before = app.user().list();
    UserData user = new UserData("Daria", "Vladimirovna", "Pyrkova", "dd", "U", "Dolgoprudny", "d@u.ru", "d@g.com", "999", "777", "888", "1", "January", "1990", "[none]");
    app.user().create(user, true);
    app.goTo().homePage();
    List<UserData> after = app.user().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    user.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(user);
    Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
