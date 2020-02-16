package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.TestBase;
import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.gotoGroupPage();
    app.selectElement();
    app.deleteSelectedGroups();
    app.returnToGroupPage();
  }

}
