package dd.pyrkova.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectHelper {
  private WebDriver wd;

  public SelectHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void selectElement() {
    wd.findElement(By.xpath("(//input[@name='selected[]'])[4]")).click();
  }
}
