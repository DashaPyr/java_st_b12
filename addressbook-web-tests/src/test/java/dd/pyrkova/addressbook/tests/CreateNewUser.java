package dd.pyrkova.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import dd.pyrkova.addressbook.model.*;
import org.testng.annotations.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateNewUser extends TestBase {

  @DataProvider
  public Iterator<Object[]> validUsersFromXml() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resouces/users.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null){
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(UserData.class);
    List<UserData> users = (List<UserData>) xstream.fromXML(xml);
    return users.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validUsersFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resouces/users.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null){
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<UserData> users = gson.fromJson(json, new TypeToken<List<UserData>>(){}.getType()); //List<GroupData>.class
    return users.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @Test (dataProvider = "validUsersFromJson")
  public void testCreateNewUser(UserData user) {
    app.goTo().homePage();
    File photo = new File("src/test/resouces/catbus.jpg");
    Users before = app.user().allUser();
/*    UserData user = new UserData().withFirstname(firstname).withMiddlename(middlename).withLastname(lastname).withNickname("dd")
            .withCompany("U").withAddress("Dolgoprudny").withEmailone("d@u.ru").withEmailtwo("d@g.com")
            .withPhonehome("999").withPhonemobile("777").withPhonework("888")
            .withBirthday("1").withBirthmonth("January").withBirthyear("1990").withGroup("[none]").withPhoto(photo); */
    app.user().create(user, true);
    app.goTo().homePage();
    assertThat(app.user().userCount(), equalTo(before.size() + 1));
    Users after = app.user().allUser();
    assertThat(after, equalTo(
            before.withAdded(user.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resouces/catbus.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

}
