package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.GroupData;
import dd.pyrkova.addressbook.model.Groups;
import dd.pyrkova.addressbook.model.TestBase;
import org.testng.annotations.*;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resouces/groups.csv")));
    String line = reader.readLine();
    while (line != null){
      String[] split = line.split(";");
      list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.group().allGroup();
    app.group().create(group);
    Groups after = app.group().allGroup();
    assertThat(app.group().groupCount(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
          before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

/*  @Test  (enabled = false)
  public void testBadGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().allGroup();
    GroupData group = new GroupData().withName("test35'").withHeader("test2").withFooter("test3");
    app.group().create(group);
    assertThat(app.group().groupCount(), equalTo(before.size()));
    Groups after = app.group().allGroup();
    assertThat(after, equalTo(before));
  }*/
}
