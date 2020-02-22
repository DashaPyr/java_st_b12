package dd.pyrkova.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectHelper extends HelperBase {

  public SelectHelper(WebDriver wd) {
    super(wd);
  }

  public void selectElement() {
   click(By.name("selected[]"));
  }

  public boolean isThereAGroup() {
    return isGroupElementPresent(By.name("selected[]"));
  }
}
