package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddUserToGroup extends TestBase {

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

    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }

  @Test
  public void testAddUserToGroup() {
    Users usersAll = app.db().users();
 //   UserData selectUser = before.iterator().next();
    Groups groupsAll = app.db().groups();

    UserData userSelect = null;
    GroupData groupSelect = null;
    UserData userAfter = null;

    for (UserData currentUser : usersAll){
      Groups groupsOfSelecteUser = currentUser.getGroups();
      if(groupsOfSelecteUser.size() != groupsAll.size()){
        groupsAll.removeAll(groupsOfSelecteUser);
        groupSelect = groupsAll.iterator().next();
        userSelect = currentUser;
        break;
      }
    }
    if (groupSelect == null) {
      UserData user = new UserData().withFirstname("Daria").withMiddlename("Vladimirovna").withLastname("Pyrkova").withEmailone("d@u.ru")
              .withPhonehome("999").withPhonemobile("777").withPhonework("888")
              .withBirthday("1").withBirthmonth("January").withBirthyear("1990");
      app.user().create(user, true);
      Users userA = app.db().users();
      user.withId(userA.stream().mapToInt((g) -> (g).getId()).max().getAsInt());
      userSelect = user;
      groupSelect = groupsAll.iterator().next();
    }

    app.goTo().homePage();
    app.user().allGroupsOnUserPage();
    app.user().selectGroup(userSelect, groupSelect);
    app.goTo().homePage();

    Users usersAllAfter= app.db().users();
    for (UserData currentUserAfter : usersAllAfter){
      if (currentUserAfter.getId() == userSelect.getId()){
        userAfter = currentUserAfter;
      }
    }

    assertThat(userSelect.getGroups(), equalTo(userAfter.getGroups().without(groupSelect)));
  }
}
