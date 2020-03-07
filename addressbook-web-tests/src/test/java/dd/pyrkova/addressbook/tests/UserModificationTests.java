package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.TestBase;
import dd.pyrkova.addressbook.model.UserData;
import dd.pyrkova.addressbook.model.Users;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void preconditions (){
    app.goTo().homePage();
    if (app.user().allUser().size() == 0){
      app.user().create(new UserData().withFirstname("Daria").withMiddlename("Vladimirovna").withLastname("Pyrkova").withNickname("dd")
              .withCompany("U").withAddress("Dolgoprudny").withEmailone("d@u.ru").withEmailtwo("d@g.com")
              .withPhonehome("999").withPhonemobile("777").withPhonework("888")
              .withBirthday("1").withBirthmonth("January").withBirthyear("1990").withGroup("[none]"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testGroupModification() {
    Users before = app.user().allUser();
    UserData modifiedUser = before.iterator().next();
    UserData user = new UserData().withId(modifiedUser.getId()).withFirstname("Daria").withMiddlename("Vladimirovna").withLastname("Pyrkova").withNickname("dd")
            .withCompany("U").withAddress("Dolgoprudny").withEmailone("d@u.ru").withEmailtwo("d@g.com")
            .withPhonehome("999").withPhonemobile("777").withPhonework("888")
            .withBirthday("1").withBirthmonth("January").withBirthyear("1990");
    app.user().modify(user);
    app.goTo().homePage();
    Users after = app.user().allUser();
    assertEquals(after.size(), before.size());

    assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
  }


}
