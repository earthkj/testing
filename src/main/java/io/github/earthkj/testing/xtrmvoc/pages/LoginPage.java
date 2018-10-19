package io.github.earthkj.testing.xtrmvoc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.annotations.Test;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(how=How.ID, using="userId") WebElement inputUserId;
	@FindBy(how=How.ID, using="passwordView") WebElement inputPassword;
	@FindBy(how=How.LINK_TEXT, using="LOGIN") WebElement btnLogin;
	@FindBy(how=How.LINK_TEXT, using="확 인") WebElement btnConfirm;
	
	public void inputUserId() {
		inputUserId.click();
		inputUserId.clear();
		// TODO retrieve ID and PW from DB. Do not hardcode
		inputUserId.sendKeys("MYID");
	}
	
	public void inputPassword() {
		inputPassword.click();
		inputPassword.clear();
		// TODO retrieve ID and PW from DB. Do not hardcode
		inputPassword.sendKeys("PASSWORD");
	}
	
	public void clickLoginButton() {
		btnLogin.click();
	}
	
	public void clickLoginConfirmButton() {
		btnConfirm.click();
	}
}
