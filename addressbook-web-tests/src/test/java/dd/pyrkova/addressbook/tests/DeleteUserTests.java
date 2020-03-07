package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.TestBase;
import dd.pyrkova.addressbook.model.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteUserTests extends TestBase {

  @BeforeMethod
  public void preconditions(){
    app.goTo().homePage();
    if (app.user().list().size() == 0){
      app.user().create(new UserData("Daria", "Vladimirovna", "Pyrkova", "dd", "U", "Dolgoprudny", "d@u.ru", "d@g.com", "999", "777", "888", "1", "January", "1990", "[none]"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testDeleteUser() throws Exception {
    List<UserData> before = app.user().list();
    int index = before.size() - 1;
    app.user().delete(index);
    app.goTo().homePage();
    List<UserData> after = app.user().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }


}
