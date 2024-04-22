package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	
	//page class/ page library
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. private by locators
	
	By logoutLink = By.linkText("Logout");
	By myAccoutLink = By.linkText("My Account");
	By headers = By.cssSelector("div #content h2");
	By search = By.name("search");
	By searchIcon = By.cssSelector("div #search button");
	
	//2. public page class constructor
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil =new ElementUtil(driver);
	}
	
	//3. public page Actions/Methods
	public String getActPageTitle() {
		String title  = eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, 5);
		System.out.println("Account page title is  " + title);
		return title;
	}
	
	public String getActPageURL() {
		String url  = eleUtil.waitForURLContains(AppConstants.ACCOUNT_PAGE_FRACTION, 5);
		System.out.println("Account page URl is " + url);
		return url;
		
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, 5).isDisplayed();
	}
	
	public boolean isMyAccountLinkExist() {
		return eleUtil.waitForElementVisible(myAccoutLink, 5).isDisplayed();
		
	}
	
	public ArrayList<String> getAccountPageHeadersList() {
		List<WebElement> headersEleList  = eleUtil.getElements(headers);
		ArrayList<String> headerList = new ArrayList<String>();
		
		for(WebElement e : headersEleList) {
			String header = e.getText(); 
			headerList.add(header);
			
		}
		return headerList;
	}
	
	public SearchResultPage doSearch(String searchKey) {
		System.out.println("Searching Key : " + searchKey);
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultPage(driver);
	}
	

}
