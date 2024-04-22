package com.qa.opencart.tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

public class LoginPageTest extends BaseTest{
	
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);	
		
	}
	
	@Test(priority = 2)
	public void loginPageURLTest() {
		String acturl = loginPage.getLoginPageURL();
		Assert.assertTrue(acturl.contains(AppConstants.LOGIN_PAGE_FRACTION), AppError.URL_NOT_FOUND);
		
		
	}
	
	@Test(priority = 3)
	public void forgotPWDLinkExistTest() {
		assertTrue(loginPage.isForgotPWLinkExist());
	}
	
	@Test(priority = 4)
	public void loinTest() {
		accPage = loginPage.doLogin("dec2023@opencart.con", "Selenium@12345");
		Assert.assertEquals(accPage.getActPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	
	
	
}
