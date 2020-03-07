package dd.pyrkova.addressbook.appmanager;

import dd.pyrkova.addressbook.model.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

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
    wd.switchTo().alert().accept();
//    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void initUserModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void submitUserModification() {
    click(By.xpath("//input[@name='update']"));
  }

  public void selectUser(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public boolean isThereAUser() {
    return isUserElementPresent(By.name("selected[]"));
  }

  public void createUser(UserData user, boolean b) {
    gotoNewUserPage();
    fillInUserData(user, b);
    submitUserCreation();
  }

  public int getUserCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<UserData> getUserList() {
    List<UserData> users = new ArrayList<UserData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lname = cells.get(1).getText();
      String fname = cells.get(2).getText();
      String addr = cells.get(3).getText();
      UserData user = new UserData(id, fname, null, lname, null, null, addr, null, null, null, null, null, null, null, null, null);
      users.add(user);
    }
    return users;
  }
}