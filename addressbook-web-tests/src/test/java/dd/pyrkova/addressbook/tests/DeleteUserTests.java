package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.TestBase;
import dd.pyrkova.addressbook.model.UserData;
import dd.pyrkova.addressbook.model.Users;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteUserTests extends TestBase {

  @BeforeMethod
  public void preconditions(){
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
  public void testDeleteUser() throws Exception {
    Users before = app.user().allUser();
    UserData deletedUser = before.iterator().next();
    app.user().delete(deletedUser);
    app.goTo().homePage();
    Users after = app.user().allUser();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedUser);
    assertThat(after, equalTo(before.without(deletedUser)));
    Assert.assertEquals(before, after);
  }


}
