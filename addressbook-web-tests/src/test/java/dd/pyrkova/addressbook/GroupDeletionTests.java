package dd.pyrkova.addressbook;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {
    gotoGroupPage();
    selectElement();
    deleteSelectedGroups();
    returnToGroupPage();
  }

}
