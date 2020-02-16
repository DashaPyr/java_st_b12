package dd.pyrkova.addressbook;

import org.testng.annotations.*;

public class CreateNewUser extends TestBase {

  @Test
  public void testCreateNewUser() throws Exception {

    gotoNewUserPage();
    fillInUserName(new UserNameData("Daria", "Vladimirovna", "Pyrkova", "dd"));
    fillInCompany("U");
    fillInAddress("Dolgoprudny");
    fillInPhones(new PhoneData("999", "777", "888"));
    fillInEmail(new EmailData("d@u.ru", "d@g.com"));
    fillInBirthday(new BirthData("1", "January", "1990"));
    submitUserCreation();
    returnToHomePage();
    logout();
  }

}
