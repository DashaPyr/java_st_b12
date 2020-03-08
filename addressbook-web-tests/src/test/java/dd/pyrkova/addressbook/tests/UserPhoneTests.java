package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.TestBase;
import dd.pyrkova.addressbook.model.UserData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserPhoneTests extends TestBase {

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
  public void testUserPhones(){
    app.goTo().homePage();
    UserData user = app.user().allUser().iterator().next();
    UserData userInfoFroEditForm = app.user().infoFromUserForm(user);

    assertThat(user.getPhonehome(), equalTo(cleaned(userInfoFroEditForm.getPhonehome())));
    assertThat(user.getPhonemobile(), equalTo(cleaned(userInfoFroEditForm.getPhonemobile())));
    assertThat(user.getPhonework(), equalTo(cleaned(userInfoFroEditForm.getPhonework())));
  }

  public String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]","");
  }
}
