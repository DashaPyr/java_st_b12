package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.GroupData;
import dd.pyrkova.addressbook.model.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test22", "test33"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());
  }
}
