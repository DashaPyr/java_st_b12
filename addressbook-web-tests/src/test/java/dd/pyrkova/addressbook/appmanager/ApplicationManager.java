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
  private CloseAlertHelper closeAlertHelper;
  private SelectHelper selectHelper;
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

    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/group.php");
    groupHelper = new GroupHelper(wd);
    userHelper = new UserHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    selectHelper = new SelectHelper(wd);
    closeAlertHelper = new CloseAlertHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public UserHelper getUserHelper() {
    return userHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public SelectHelper getSelectHelper() {
    return selectHelper;
  }

  public CloseAlertHelper getCloseAlertHelper() {
    return closeAlertHelper;
  }

  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }
}
