package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.TestBase;
import org.testng.annotations.Test;

public class DeleteUserTests extends TestBase {

  @Test
  public void testDeleteUser() throws Exception {
    app.returnToHomePage();
    app.selectElement();
    app.deleteSelectedUser();
    app.closeAlertDialog();
    app.returnToHomePage();
    app.logout();
  }

}
