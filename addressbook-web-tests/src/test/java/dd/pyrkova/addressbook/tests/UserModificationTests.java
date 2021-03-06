package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void preconditions (){
    Groups groups = app.db().groups();
    if (app.db().users().size() == 0){
      app.goTo().homePage();
      app.user().create(new UserData().withFirstname("Daria").withMiddlename("Vladimirovna").withLastname("Pyrkova").withNickname("dd")
              .withCompany("U").withAddress("Dolgoprudny").withEmailone("d@u.ru").withEmailtwo("d@g.com")
              .withPhonehome("999").withPhonemobile("777").withPhonework("888")
              .withBirthday("1").withBirthmonth("January").withBirthyear("1990").inGroup(groups.iterator().next()), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testGroupModification() {
    Groups groups = app.db().groups();
    Users before = app.db().users();
    UserData modifiedUser = before.iterator().next();
    UserData user = new UserData().withId(modifiedUser.getId()).withFirstname("Daria").withMiddlename("Vladimirovna").withLastname("Pyrkova").withNickname("dd")
            .withCompany("U").withAddress("Dolgoprudny").withEmailone("d@u.ru").withEmailtwo("d@g.com")
            .withPhonehome("999").withPhonemobile("777").withPhonework("888")
            .withBirthday("1").withBirthmonth("January").withBirthyear("1990").inGroup(groups.iterator().next());
    app.goTo().homePage();
    app.user().modify(user);
    app.goTo().homePage();
//    assertThat(app.user().userCount(), equalTo(before.size()));
    Users after = app.db().users();
    assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
    verifyUserListUI();
  }
}
