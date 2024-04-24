package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class LoginPage {
	
	//page class/ page library
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. private by locators
	By emailId = By.id("input-email");
	By password = By.id("input-password");
	By loginButton = By.xpath("//input[@value='Login']");
	By forgotPWLink = By.linkText("Forgotten Password");
	By registerLink = By.linkText("Register");
	//By test = By.linkText("test");
	
	 
	
	//2. public page class constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. public page Actions/Methods
	public String getLoginPageTitle() {
		String title  = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_SHORT_TIME);
		System.out.println("Login page title is  " + title);
		return title;
	}
	
	public String getLoginPageURL() {
		String url  = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION, TimeUtil.DEFAULT_MEDIUM_TIME);
		//System.out.println("Login page URl is " + url);
		Log.info("Login page URl is " + url);
		return url;
		
	}
	
	public boolean isForgotPWLinkExist() {
		return eleUtil.isElementDisplayed(forgotPWLink);

	}
	
	public AccountPage doLogin(String username, String pwd) {
		System.out.println("Login crdentials are " + username + " and " + pwd);
		eleUtil.waitForElementVisible(emailId, TimeUtil.DEFAULT_LONG_TIME).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
	
		return new AccountPage(driver);
		
	}
	
	public RegistrationPage navigateToRegisterPage() {
		eleUtil.waitForElementVisible(registerLink, TimeUtil.DEFAULT_LONG_TIME).click();
		return new RegistrationPage(driver);
		
		
		
	}
	 

}
