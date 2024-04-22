package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	//page class/ page library
		private WebDriver driver;
		private ElementUtil eleUtil;
		
		private Map<String, String> productMap = new HashMap<String, String>(); 
		
		//1. private by locators
		
		private By productHeader = By.tagName("h1");
		private By images  = By.cssSelector("ul.thumbnails img");
		private By productMetaDeta = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
		private By productPriceDeta = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
		
		//2. public page class constructor
		public ProductInfoPage(WebDriver driver) {
			this.driver = driver;
			eleUtil =new ElementUtil(driver);
		}
		
		public String getProductHeader() {
			String header = eleUtil.doGetElementText(productHeader);
			System.out.println(header);
			return header;
		}
		
		
		public int getProductImageCount() {
			int totalImages = eleUtil.waitForElementsVisible(images, 10).size();
			System.out.println("Image Count for " + getProductHeader() + " : " + totalImages);
			return totalImages;
			
		}
		
//		Brand: Apple
//		Product Code: Product 17
//		Reward Points: 700
//		Availability: Out Of Stock
		
		private void getProductMetaDeta() {
			List<WebElement> metaList = eleUtil.getElements(productMetaDeta);
			for(WebElement e : metaList) {
				String text = e.getText();
				String metaKey = text.split(":")[0].trim();
				String metaValue = text.split(":")[1].trim();
				productMap.put(metaKey, metaValue);
				
				
			}
		}
		
//		$1,202.00
//		Ex Tax: $1,000.00
		private void productPriceDeta() {
			List<WebElement> priceList = eleUtil.getElements(productPriceDeta);
			String price = priceList.get(0).getText();
			String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
			productMap.put("productPrice", price);
			productMap.put("exTaxPrice", exTaxPrice);
			
			
		}
		
		public Map<String, String> getProductDetailsMap() {
			productMap.put("Header", getProductHeader());
			productMap.put("ProductImages", String.valueOf(getProductImageCount()));
			getProductMetaDeta();
			productPriceDeta();
			return productMap;
			
			
			
			
		}

}
