package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.GroupData;
import dd.pyrkova.addressbook.model.TestBase;
import dd.pyrkova.addressbook.model.UserData;
import dd.pyrkova.addressbook.model.Users;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddUserToGroup extends TestBase {
 /* @BeforeMethod
  public void preconditions (){
    if (app.db().users().size() == 0){
      app.goTo().homePage();
      app.user().create(new UserData().withFirstname("Daria").withMiddlename("Vladimirovna").withLastname("Pyrkova").withNickname("dd")
              .withCompany("U").withAddress("Dolgoprudny").withEmailone("d@u.ru").withEmailtwo("d@g.com")
              .withPhonehome("999").withPhonemobile("777").withPhonework("888")
              .withBirthday("1").withBirthmonth("January").withBirthyear("1990"), true);
      app.goTo().homePage();
    }
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }*/

  @Test
  public void testAddUserToGroup() {
    Users before = app.db().users();
    UserData selectUser = before.iterator().next();
//    app.user().selectGroupStatic(selectUser);
    app.user().selectGroup(selectUser, true);
    app.goTo().homePage();
/*    assertThat(app.user().userCount(), equalTo(before.size() - 1));
    Users after = app.db().users();
    assertThat(after, equalTo(before.without(selectUser)));
    verifyUserListUI();*/
  }
}
