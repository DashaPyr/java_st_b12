package dd.pyrkova.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isGroupElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isGroupElementPresent(By.name("new"))){
      return;
    }
    click(By.linkText("groups"));
  }

  public void homePage() {
    if (isUserElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }

}
