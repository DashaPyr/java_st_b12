package dd.pyrkova.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectHelper extends HelperBase {

  public SelectHelper(WebDriver wd) {
    super(wd);
  }

  public void selectElement() {
   click(By.xpath("//input[@name='selected[]']"));
  }

  public boolean isThereAElement() {
    return isElementPresent(By.xpath("//input[@name='selected[]']"));
  }
}
