package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.*;
import org.testng.annotations.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateNewUser extends TestBase {

  @DataProvider
  public Iterator<Object[]> validUsers(){
    List<Object[]> list = new ArrayList<Object[]>();
    File photo = new File("src/test/resouces/catbus.jpg");
    list.add(new Object[] {new UserData().withFirstname("Darya_11").withMiddlename("V_11").withLastname("Pyrkova_11").withNickname("dd")
            .withCompany("U").withAddress("Dolgoprudny").withEmailone("d@u.ru").withEmailtwo("d@g.com")
            .withPhonehome("999").withPhonemobile("777").withPhonework("888")
            .withBirthday("1").withBirthmonth("January").withBirthyear("1990").withGroup("[none]").withPhoto(photo)});
    list.add(new Object[] {new UserData().withFirstname("Darya_22").withMiddlename("V_22").withLastname("Pyrkova_22").withNickname("dd")
            .withCompany("U").withAddress("Dolgoprudny").withEmailone("d@u.ru").withEmailtwo("d@g.com")
            .withPhonehome("999").withPhonemobile("777").withPhonework("888")
            .withBirthday("1").withBirthmonth("January").withBirthyear("1990").withGroup("[none]").withPhoto(photo)});
    list.add(new Object[] {new UserData().withFirstname("Darya_21").withMiddlename("V_21").withLastname("Pyrkova_21").withNickname("dd")
            .withCompany("U").withAddress("Dolgoprudny").withEmailone("d@u.ru").withEmailtwo("d@g.com")
            .withPhonehome("999").withPhonemobile("777").withPhonework("888")
            .withBirthday("1").withBirthmonth("January").withBirthyear("1990").withGroup("[none]").withPhoto(photo)});
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
