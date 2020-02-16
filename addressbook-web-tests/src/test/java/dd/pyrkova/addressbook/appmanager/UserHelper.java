package dd.pyrkova.addressbook.appmanager;

import dd.pyrkova.addressbook.model.BirthData;
import dd.pyrkova.addressbook.model.EmailData;
import dd.pyrkova.addressbook.model.PhoneData;
import dd.pyrkova.addressbook.model.UserNameData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class UserHelper extends HelperBase {

  public UserHelper(WebDriver wd) {
    super(wd);
  }

  public void submitUserCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillInBirthday(BirthData birthData) {
    select(By.name("bday"), birthData.getBirthday());
    select(By.name("bmonth"), birthData.getBirthmonth());
    type(By.name("byear"), birthData.getBirthyear());
  }

  public void fillInEmail(EmailData emailData) {
    type(By.name("email"), emailData.getEmailone());
    type(By.name("email2"), emailData.getEmailtwo());
  }

  public void fillInPhones(PhoneData phoneData) {
    type(By.name("home"), phoneData.getPhonehome());
    type(By.name("mobile"), phoneData.getPhonemobile());
    type(By.name("work"), phoneData.getPhonework());
  }

  public void fillInAddress(String address) {
    type(By.name("address"), address);
  }

  public void fillInCompany(String company) {
    type(By.name("company"), company);
  }

  public void fillInUserName(UserNameData userNameData) {
    type(By.name("firstname"), userNameData.getFirstname());
    type(By.name("middlename"), userNameData.getMiddlename());
    type(By.name("lastname"), userNameData.getLastname());
    type(By.name("nickname"), userNameData.getNickname());
  }

  public void gotoNewUserPage() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedUser() {
    click(By.xpath("//input[@value='Delete']"));
  }
}
