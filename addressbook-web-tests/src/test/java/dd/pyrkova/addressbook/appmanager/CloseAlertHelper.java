package dd.pyrkova.addressbook.appmanager;

import org.openqa.selenium.WebDriver;

public class CloseAlertHelper {
  private WebDriver wd;

  public CloseAlertHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void closeAlertDialog() {
    wd.switchTo().alert().accept();
  }
}
