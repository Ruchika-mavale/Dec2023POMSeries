package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public  void regSetUp(){
		registrationPage = loginPage.navigateToRegisterPage();
		
	}
	
	@DataProvider
	public Object[][] getUserRegTestData() {
		return new Object[][] {
			{"test111", "te11st01", "7788667755", "1233", "yes"},
			{"test112", "te11st02", "7788667756", "1234", "yes"},
			{"test113", "te11st03",  "7788667757", "1235", "yes"}
		};
		
	}
	
	@DataProvider
	public Object[][] getUserRegTestDataFromExcel() {
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		
	}
	
//	@Test(dataProvider = "getUserRegTestDataFromExcel")
//	public void userRegTest(String firstName, String lastName, String telephone, String password, String subscribe) {
//		
//		Assert.assertTrue(registrationPage.userRegister(firstName, lastName, StringUtils.getRandomEmailId(), telephone,  password , subscribe));
//	}
	
	@Test(dataProvider = "getUserRegTestData")
	public void userRegTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		
		Assert.assertTrue(registrationPage.userRegister(firstName, lastName, StringUtils.getRandomEmailId(), telephone,  password , subscribe));
	}

}
