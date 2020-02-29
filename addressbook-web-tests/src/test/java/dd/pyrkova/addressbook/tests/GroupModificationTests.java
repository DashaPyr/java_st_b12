package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.GroupData;
import dd.pyrkova.addressbook.model.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test22", "test33"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
//    app.getSessionHelper().logout();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);
  }
}
