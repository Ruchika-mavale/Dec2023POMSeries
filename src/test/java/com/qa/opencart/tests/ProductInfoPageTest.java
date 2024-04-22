package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductInfoPage;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void infoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductSearchDate() {
		return new Object[][] {
			{"macbook" , "MacBook Pro"},
			{"imac" , "iMac"},
			{"samsung" , "Samsung SyncMaster 941BW"},
			{"samsung" , "Samsung Galaxy Tab 10.1"}
			
		};
	}
	
	
	@DataProvider
	public Object[][] getProductImageDate() {
		return new Object[][] {
			{"macbook" , "MacBook Pro", 4},
			{"imac" , "iMac" , 3},
			{"samsung" , "Samsung SyncMaster 941BW" , 1},
			{"samsung" , "Samsung Galaxy Tab 10.1" , 7}
			
		};
	}
	
	@Test(dataProvider = "getProductSearchDate")
	public void ProductHeaderTest(String searchKey , String productName) {
		searchResutPage = accPage.doSearch(searchKey);
		productInfoPage  = searchResutPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(), productName);
	}
	
	@Test(dataProvider = "getProductImageDate")
	public void ProductImagesCountTest(String searchKey , String productName, int imgCnt) {
		searchResutPage = accPage.doSearch(searchKey);
		productInfoPage  = searchResutPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImageCount(), imgCnt);
		
	}
	
	@Test
	public void ProductInfoTest() {
		searchResutPage = accPage.doSearch("macbook");
		productInfoPage  = searchResutPage.selectProduct("MacBook Air");
		Map<String, String> productActDetailsMap  = productInfoPage.getProductDetailsMap();
		softAssert.assertEquals(productActDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productActDetailsMap.get("Reward Points"), "700");
		softAssert.assertEquals(productActDetailsMap.get("productPrice"), "$1,202.00");
		softAssert.assertEquals(productActDetailsMap.get("exTaxPrice"), "$1,000.00");
		softAssert.assertAll();
	
		
		
	}

}
