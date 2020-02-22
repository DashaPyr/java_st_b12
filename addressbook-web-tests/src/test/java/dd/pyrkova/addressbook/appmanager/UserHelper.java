package dd.pyrkova.addressbook.appmanager;

import dd.pyrkova.addressbook.model.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class UserHelper extends HelperBase {

  public UserHelper(WebDriver wd) {
    super(wd);
  }

  public void submitUserCreation() {
    click(By.xpath("//input[@name='submit']"));
  }

  public void fillInUserData(UserData userData, boolean creation ) {
    type(By.name("firstname"), userData.getFirstname());
    type(By.name("middlename"), userData.getMiddlename());
    type(By.name("lastname"), userData.getLastname());
    type(By.name("nickname"), userData.getNickname());
    type(By.name("company"), userData.getCompany());
    type(By.name("address"), userData.getAddress());
    type(By.name("email"), userData.getEmailone());
    type(By.name("email2"), userData.getEmailtwo());
    type(By.name("home"), userData.getPhonehome());
    type(By.name("mobile"), userData.getPhonemobile());
    type(By.name("work"), userData.getPhonework());
    select(By.name("bday"), userData.getBirthday());
    select(By.name("bmonth"), userData.getBirthmonth());
    type(By.name("byear"), userData.getBirthyear());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
    } else {
      Assert.assertFalse(isGroupElementPresent(By.name("new_group")));
    }
  }

  public void gotoNewUserPage() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedUser() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void initUserModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitUserModification() {
    click(By.xpath("//input[@name='update']"));
  }

  public void selectUser() { click(By.name("selected[]")); }

  public boolean isThereAUser() {
    return isUserElementPresent(By.name("selected[]"));
  }

  public void createUser(UserData user, boolean b) {
    gotoNewUserPage();
    fillInUserData(user, b);
    submitUserCreation();
  }
}
