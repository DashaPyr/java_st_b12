package dd.pyrkova.addressbook.model;

import dd.pyrkova.addressbook.appmanager.ApplicationManager;
import org.hamcrest.Matchers;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.google.common.base.Predicates.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  public static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.IE));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart (Method m, Object [] p){
    logger.info("Start test " + m.getName() + " with parameters" + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop (Method m){
    logger.info("Stop test " + m.getName());
  }

  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().allGroup();
      assertThat(uiGroups, Matchers.equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

  public void verifyUserListUI() {
    if (Boolean.getBoolean("verifyUI")){
      Users dbUsers = app.db().users();
      Users uiUsers = app.user().allUser();
      assertThat(uiUsers.stream().map((uu) -> new UserData().withId(uu.getId()).withFirstname(uu.getFirstname()).withLastname(uu.getLastname())
              .withAddress(uu.getAddress())).collect(Collectors.toSet()), Matchers.equalTo(dbUsers.stream()
              .map((ub) -> new UserData().withId(ub.getId()).withFirstname(ub.getFirstname()).withLastname(ub.getLastname())
                      .withAddress(ub.getAddress())).collect(Collectors.toSet())));
    }
  }
}


