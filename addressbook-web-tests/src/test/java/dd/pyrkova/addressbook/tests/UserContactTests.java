package dd.pyrkova.addressbook.tests;

import com.sun.org.apache.xpath.internal.objects.XObject;
import dd.pyrkova.addressbook.model.TestBase;
import dd.pyrkova.addressbook.model.UserData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserContactTests extends TestBase {

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

    assertThat(user.getAllEmails(), equalTo(mergeEmails(userInfoFroEditForm)));
    assertThat(user.getAllPhones(), equalTo(mergePhones(userInfoFroEditForm)));
  }

  private String  mergeEmails(UserData user) {
    return Arrays.asList(user.getEmailone(), user.getEmailtwo())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  private String mergePhones(UserData user) {
    return Arrays.asList(user.getPhonehome(), user.getPhonemobile(), user.getPhonework())
            .stream().filter((s) -> ! s.equals(""))
            .map(UserContactTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]","");
  }
}
