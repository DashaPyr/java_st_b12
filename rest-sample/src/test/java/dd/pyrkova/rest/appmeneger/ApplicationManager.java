package dd.pyrkova.rest.appmeneger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  protected WebDriver wd;
  private String browser;
  private Properties properties;

  public ApplicationManager(String browser) {
    this.browser = browser;
    this.properties = new Properties();
  }
}