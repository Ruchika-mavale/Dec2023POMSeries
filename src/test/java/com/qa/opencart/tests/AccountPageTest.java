package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		String accTitle = accPage.getActPageTitle();
		Assert.assertEquals(accTitle, AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void accPageURLTest() {
		String accURL = accPage.getActPageURL();
		
		Assert.assertTrue(accURL.contains(AppConstants.ACCOUNT_PAGE_FRACTION));
		
		
	}
	
	
	@Test
	public void isLogoutLinkExist() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void isMyAccountLinkExist() {
		Assert.assertTrue(accPage.isMyAccountLinkExist());
	}
	
	public void getAccountPageHeadersListTest() {
		List<String> actHeadersList = accPage.getAccountPageHeadersList();
		System.out.println(actHeadersList);
	}
	
	public void searchTest() {
		accPage.doSearch("macbook");
	}
	
	

}
