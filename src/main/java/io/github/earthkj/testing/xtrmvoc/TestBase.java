package io.github.earthkj.testing.xtrmvoc;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.Dimension;

public class TestBase {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private Actions actions;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);
		// chrome에서는 동작 안한다....
	  driver = new ChromeDriver(); 
//	  driver = new InternetExplorerDriver(caps);
	  baseUrl = "https://www.katalon.com/";
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  actions = new Actions(driver);
	}

	@Test
	public void testcase001() throws Exception {
	  driver.get("http://10.10.134.143:8080/xtrmvoc/");
	  
	  //화면 크기를 기획 된 사이즈에 맞게 조절한다
	  driver.manage().window().setSize(new Dimension(1280, 900));;
	  
	  driver.findElement(By.id("userId")).click();
	  driver.findElement(By.id("userId")).clear();
	  driver.findElement(By.id("userId")).sendKeys("MYID");
	  driver.findElement(By.id("passwordView")).click();
	  driver.findElement(By.id("passwordView")).clear();
	  driver.findElement(By.id("passwordView")).sendKeys("PASSWORD");
	  //TODO IE에서는 이 linkText를 못찾는거 같은데..?
	  driver.findElement(By.linkText("LOGIN")).click();
	  driver.findElement(By.linkText("확 인")).click();
	  
	  //katalon recorder에서는 hovering 안해도 잘 되는데, webdriver로 하니깐 move 해줘야한다
	  //https://seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/interactions/Actions.html
	  actions.moveToElement(driver.findElement(By.linkText("어드바이저"))).perform();
	  driver.findElement(By.linkText("상담사모니터링")).click();
	  
	  //마찬가지로 move 추가....했더니, alarm icon하고 겹치면서 호버링이 안되는 사태 발생
	  //moveToElement가 인자가 없으면 center of element, 주고싶으면 offset x,y를 줘야한단다..
	  //일단 이렇게 해결이 될 지언정, 지금 화면 배율 이상하게 나와서...좀 수정은 해야할듯..
	  actions.moveToElement(driver.findElement(By.linkText("정보관리")), 10, 10).perform();
	  driver.findElement(By.linkText("접속로그현황")).click();
	  //이유는 모르겠는데 linkText"조 회"를 못 찾는다...해서 selector를 바꿔줬더니 정상 동작하는건 확인..
	  try{
		  //이유는 모르겠는데, 찾은 녀석이 stale 이라고 뜬다...class로 찾은게 문제인가...?
		  //https://www.seleniumhq.org/exceptions/stale_element_reference.jsp
		  //FIXME 임시로 재시도 하는 방식으로 해결
		  driver.findElement(By.className("search")).click();
	  }catch(StaleElementReferenceException e) {
		  System.out.println("Stale!!!");
		  driver.findElement(By.className("search")).click();
	  }
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
	  driver.quit();
	  String verificationErrorString = verificationErrors.toString();
	  if (!"".equals(verificationErrorString)) {
	    fail(verificationErrorString);
	  }
	}

	private boolean isElementPresent(By by) {
	  try {
	    driver.findElement(by);
	    return true;
	  } catch (NoSuchElementException e) {
	    return false;
	  }
	}

	private boolean isAlertPresent() {
	  try {
	    driver.switchTo().alert();
	    return true;
	  } catch (NoAlertPresentException e) {
	    return false;
	  }
	}

	private String closeAlertAndGetItsText() {
	  try {
	    Alert alert = driver.switchTo().alert();
	    String alertText = alert.getText();
	    if (acceptNextAlert) {
	      alert.accept();
	    } else {
	      alert.dismiss();
	    }
	    return alertText;
	  } finally {
	    acceptNextAlert = true;
	  }
	}
}
