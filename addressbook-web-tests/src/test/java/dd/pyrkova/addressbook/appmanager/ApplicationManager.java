package dd.pyrkova.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  protected WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private UserHelper userHelper;
  private GroupHelper groupHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
 //   String browser = BrowserType.CHROME;
    if (browser.equals(BrowserType.CHROME)){
      System.setProperty("webdriver.chrome.driver", "c:\\Windows\\System32\\chromedriver.exe");
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)){
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.IE)){
      wd = new InternetExplorerDriver();
    }

    wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(wd);
    userHelper = new UserHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public UserHelper user() {
    return userHelper;
  }

  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }
}
