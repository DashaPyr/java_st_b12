package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.TestBase;
import org.testng.annotations.Test;

public class DeleteUserTests extends TestBase {

  @Test
  public void testDeleteUser() throws Exception {
    app.getNavigationHelper().returnToHomePage();
    app.getSelectHelper().selectElement();
    app.getUserHelper().deleteSelectedUser();
    app.getCloseAlertHelper().closeAlertDialog();
    app.getNavigationHelper().returnToHomePage();
    app.getSessionHelper().logout();
  }

}
