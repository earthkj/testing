package io.github.earthkj.testing.xtrmvoc.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import io.github.earthkj.testing.xtrmvoc.pages.LoginPage;


public class LoginTest extends TestBase{
	
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	@Test
	public void testcase001() throws Exception {
		loginPage.inputUserId();
		loginPage.inputPassword();
		loginPage.clickLoginButton();
		loginPage.clickLoginConfirmButton();
		
		//이하
		
		// katalon recorder에서는 hovering 안해도 잘 되는데, webdriver로 하니깐 move 해줘야한다
		// https://seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/interactions/Actions.html
		actions.moveToElement(driver.findElement(By.linkText("어드바이저"))).perform();
		driver.findElement(By.linkText("상담사모니터링")).click();
		
		// 마찬가지로 move 추가....했더니, alarm icon하고 겹치면서 호버링이 안되는 사태 발생
		// moveToElement가 인자가 없으면 center of element, 주고싶으면 offset x,y를 줘야한단다..
		// 일단 이렇게 해결이 될 지언정, 지금 화면 배율 이상하게 나와서...좀 수정은 해야할듯..
		actions.moveToElement(driver.findElement(By.linkText("정보관리")), 10, 10).perform();
		driver.findElement(By.linkText("접속로그현황")).click();
		// 이유는 모르겠는데 linkText"조 회"를 못 찾는다...해서 selector를 바꿔줬더니 정상 동작하는건 확인..
		try{
			// 이유는 모르겠는데, 찾은 녀석이 stale 이라고 뜬다...class로 찾은게 문제인가...?
			// https://www.seleniumhq.org/exceptions/stale_element_reference.jsp
			// FIXME 임시로 재시도 하는 방식으로 해결
			driver.findElement(By.className("search")).click();
		}catch(StaleElementReferenceException e) {
			System.out.println("Stale!!!");
			driver.findElement(By.className("search")).click();
		}
	}
}
