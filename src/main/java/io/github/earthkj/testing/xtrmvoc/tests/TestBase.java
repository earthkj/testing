package io.github.earthkj.testing.xtrmvoc.tests;

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
	
	public static WebDriver driver = null;
	public static Actions actions;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		//드라이버 초기화
		driver = new ChromeDriver(); 
		// driver = new InternetExplorerDriver();
		
		//페이지 오픈
		baseUrl = "http://10.10.134.143:8080/xtrmvoc/";
		driver.get(baseUrl);
		// Resizing window size
		driver.manage().window().setSize(new Dimension(1280, 900));;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		// 액션 초기화
		actions = new Actions(driver);
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
