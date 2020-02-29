package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashSet;
import java.util.List;

public class CreateNewUser extends TestBase {

  @Test
  public void testCreateNewUser() throws Exception {
    app.getNavigationHelper().returnToHomePage();
    List<UserData> before = app.getUserHelper().getUserList();
//    int before = app.getUserHelper().getUserCount();
    UserData user = new UserData("Daria", "Vladimirovna", "Pyrkova", "dd", "U", "Dolgoprudny", "d@u.ru", "d@g.com", "999", "777", "888", "1", "January", "1990", "[none]");
    app.getUserHelper().createUser(user, true);
    app.getNavigationHelper().returnToHomePage();
    List<UserData> after = app.getUserHelper().getUserList();
//    int after = app.getUserHelper().getUserCount();
    Assert.assertEquals(after.size(), before.size() + 1);

    int max = 0;
    for (UserData u : after){
      if (u.getId() > max){
        max = u.getId();
      }
    }

    user.setId(max);
    before.add(user);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
