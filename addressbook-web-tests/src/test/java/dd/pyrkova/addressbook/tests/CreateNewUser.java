package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.*;
import org.testng.annotations.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateNewUser extends TestBase {

  @DataProvider
  public Iterator<Object[]> validUsers() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
//    File photo = new File("src/test/resouces/catbus.jpg");
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resouces/users.csv")));
    String line = reader.readLine();
    while (line != null){
      String[] split = line.split(";");
      list.add(new Object[] {new UserData().withFirstname(split[0]).withMiddlename(split[1]).withLastname(split[2])
              .withPhonehome(split[3]).withEmailone(split[4])
              .withBirthday(split[5]).withBirthmonth(split[6]).withBirthyear(split[7])
              .withGroup(split[8])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test (dataProvider = "validUsers")
  public void testCreateNewUser(UserData user) {
    app.goTo().homePage();
//    File photo = new File("src/test/resouces/catbus.jpg");
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
