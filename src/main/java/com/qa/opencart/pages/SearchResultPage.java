package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. private by locators
	
	private By searchProducts = By.cssSelector("div.product-thumb");
	
	//2. public page class constructor
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil =new ElementUtil(driver);
	}
	
	public int getSearchProductCount() {
		return eleUtil.waitForElementsVisible(searchProducts, 5).size();
	}
	
	public ProductInfoPage selectProduct(String prductName) {
		System.out.println("Searching for the Product : " + prductName);
		eleUtil.waitForElementVisible(By.linkText(prductName) , 10).click();
		return new ProductInfoPage(driver);
	}

}
