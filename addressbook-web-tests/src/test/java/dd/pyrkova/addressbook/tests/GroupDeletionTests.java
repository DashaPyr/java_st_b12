package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.GroupData;
import dd.pyrkova.addressbook.model.TestBase;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void preconditions() {
    app.goTo().groupPage();
    if (app.group().allGroup().size() == 0){
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Set<GroupData> before = app.group().allGroup();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().allGroup();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedGroup);
    Assert.assertEquals(before, after);
  }


}
