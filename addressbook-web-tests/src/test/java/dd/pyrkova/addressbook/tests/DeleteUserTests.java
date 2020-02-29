package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.TestBase;
import dd.pyrkova.addressbook.model.UserData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteUserTests extends TestBase {

  @Test
  public void testDeleteUser() throws Exception {
    app.getNavigationHelper().returnToHomePage();
    if (! app.getUserHelper().isThereAUser()){
      app.getUserHelper().createUser(new UserData("Daria", "Vladimirovna", "Pyrkova", "dd", "U", "Dolgoprudny", "d@u.ru", "d@g.com", "999", "777", "888", "1", "January", "1990", "[none]"), true);
      app.getNavigationHelper().returnToHomePage();
    }
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().selectUser(before.size() - 1);
    app.getUserHelper().deleteSelectedUser();
    app.getCloseAlertHelper().closeAlertDialog();
    app.getNavigationHelper().returnToHomePage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
