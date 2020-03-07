package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.GroupData;
import dd.pyrkova.addressbook.model.TestBase;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().allGroup();
    GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
    app.group().create(group);
    Set<GroupData> after = app.group().allGroup();
    assertThat(after.size(), equalTo(before.size() + 1));

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    assertThat(after, equalTo(before));
  }

}
