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
    if (app.db().users().size() == 0){
      app.goTo().homePage();
      app.user().create(new UserData().withFirstname("Daria").withMiddlename("Vladimirovna").withLastname("Pyrkova").withNickname("dd")
              .withCompany("U").withAddress("Dolgoprudny").withEmailone("d@u.ru").withEmailtwo("d@g.com")
              .withPhonehome("999").withPhonemobile("777").withPhonework("888")
              .withBirthday("1").withBirthmonth("January").withBirthyear("1990").withGroup("[none]"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testDeleteUser() throws Exception {
    Users before = app.db().users();
    UserData deletedUser = before.iterator().next();
    app.user().delete(deletedUser);
    app.goTo().homePage();
    assertThat(app.user().userCount(), equalTo(before.size() - 1));
    Users after = app.db().users();
    assertThat(after, equalTo(before.without(deletedUser)));
  }


}
