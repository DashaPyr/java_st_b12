package dd.pyrkova.addressbook;

import org.testng.annotations.Test;

public class DeleteUserTests extends TestBase{

  @Test
  public void testDeleteUser() throws Exception {
    returnToHomePage();
    selectElement();
    deleteSelectedUser();
    closeAlertDialog();
    returnToHomePage();
    logout();
  }

}
