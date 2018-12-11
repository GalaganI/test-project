package searchObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Wait;

public class TopNavigationAndSearch {
 
	private static final By TOP_SEARCH_LOCATOR = By.cssSelector("input[class*=\"header-search\"]");
	private static final By LOGO_LINK = By.cssSelector(".logo-link");
	private static final By FIND = By.cssSelector(".js-rz-search-button");
	private WebDriver driver ;
	
	public TopNavigationAndSearch(WebDriver driver){
	  this.driver = driver;
	 
 }
	public void search(String searchString) {
		WebElement search = driver.findElement(TOP_SEARCH_LOCATOR);
		search.sendKeys(searchString);
		driver.findElement(FIND).click();
		Wait.untilPageLoadComplete(driver);
		
		
		
	}
	public void returneToHomePage() {
		driver.findElement(LOGO_LINK).click();
		Wait.untilPageLoadComplete(driver);
	}
}
