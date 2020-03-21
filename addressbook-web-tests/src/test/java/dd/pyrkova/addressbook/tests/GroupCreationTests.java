package dd.pyrkova.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import dd.pyrkova.addressbook.model.GroupData;
import dd.pyrkova.addressbook.model.Groups;
import dd.pyrkova.addressbook.model.TestBase;
import org.testng.annotations.*;


import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
      }.getType()); //List<GroupData>.class
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validGroupsFromXml")
  public void testGroupCreation(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.db().groups();;
    app.group().create(group);
    Groups after = app.db().groups();
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
