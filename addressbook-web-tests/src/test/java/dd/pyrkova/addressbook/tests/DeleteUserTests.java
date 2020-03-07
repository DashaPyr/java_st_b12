package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.TestBase;
import dd.pyrkova.addressbook.model.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

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
    Set<UserData> before = app.user().allUser();
    UserData deletedUser = before.iterator().next();
    app.user().delete(deletedUser);
    app.goTo().homePage();
    Set<UserData> after = app.user().allUser();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedUser);
    Assert.assertEquals(before, after);
  }


}
