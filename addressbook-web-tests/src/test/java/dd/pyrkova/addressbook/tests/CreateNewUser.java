package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class CreateNewUser extends TestBase {

  @Test
  public void testCreateNewUser() throws Exception {
    app.goTo().homePage();
    Set<UserData> before = app.user().allUser();
    UserData user = new UserData().withFirstname("Daria").withMiddlename("Vladimirovna").withLastname("Pyrkova").withNickname("dd")
            .withCompany("U").withAddress("Dolgoprudny").withEmailone("d@u.ru").withEmailtwo("d@g.com")
            .withPhonehome("999").withPhonemobile("777").withPhonework("888")
            .withBirthday("1").withBirthmonth("January").withBirthyear("1990").withGroup("[none]");
    app.user().create(user, true);
    app.goTo().homePage();
    Set<UserData> after = app.user().allUser();
    Assert.assertEquals(after.size(), before.size() + 1);

    user.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(user);
    Assert.assertEquals(before, after);
  }

}
