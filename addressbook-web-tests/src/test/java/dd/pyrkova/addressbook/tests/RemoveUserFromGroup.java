package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RemoveUserFromGroup extends TestBase {

  @BeforeMethod
  public void preconditions (){
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }

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
  public void testDeleteUserFremGroup(){
    UserData userAfter = null;
    UserData userSelect;
    GroupData groupSelect = null;

    Groups groups = app.db().groups();
    Users users = app.db().users();
    app.goTo().homePage();
    userSelect = users.iterator().next();

    for (UserData currentUser : users){
      Groups currentGroup = currentUser.getGroups();
      if (currentGroup.size() > 0){
        userSelect = currentUser;
        groupSelect = currentUser.getGroups().iterator().next();
        break;
      }
    }

    if (userSelect.getGroups().size() == 0){
      groupSelect = groups.iterator().next();
      app.user().selectGroup(userSelect, groupSelect);
    }

    app.user().usersInGroup(groupSelect);
    app.user().selectUserById(userSelect.getId());
    app.user().removeUserFromGroup();
    app.goTo().homePage();

    Users usersAllAfter = app.db().users();
    for (UserData userChoiceAfter : usersAllAfter){
      if (userChoiceAfter.getId() == userSelect.getId()){
        userAfter = userChoiceAfter;
      }
    }

    assertThat(userSelect.getGroups(), equalTo(userAfter.getGroups().withAdded(groupSelect)));
  }
}
