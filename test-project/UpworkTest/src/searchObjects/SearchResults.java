package searchObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Wait;

public class SearchResults {
	
	public static final String SEARCH_ELEMENT_TOP ="data-view_type=[\"catalog_with_hover\"]";
	public static final String MORE_GOODS = "div[name=\"more_goods\"]";
	public static final String MORE_GOODS2 = "a.g-i-more-link";
	public static final String PROMO_TAG = ".g-tag";
	public static final String TITLE = ".g-i-tile-i-title";
	private By searchElementTopLocator = By.cssSelector(SEARCH_ELEMENT_TOP);
	private By moreGoodsLocator = By.cssSelector(MORE_GOODS);
	private By moreGoodsLocator2 = By.cssSelector(MORE_GOODS2);
	private By promoTagLocator = By.cssSelector(PROMO_TAG);
	private By titleLocator = By.cssSelector(TITLE);
	private WebDriver driver ;
	private JavascriptExecutor js ;
	
	public SearchResults(WebDriver driver){
		this.driver = driver;
		js=(JavascriptExecutor)driver;
	}
	public List<WebElement> returnAllSearchResultTags(){
		return driver.findElements(promoTagLocator);
		}
	public Boolean clickMoreGoods() {
		List<WebElement> moreGoods = driver.findElements(moreGoodsLocator);
		if(moreGoods!=null&&(!moreGoods.isEmpty())) {
			  js.executeScript("document.querySelector('"+MORE_GOODS+"').scrollIntoView();document.querySelector('"+MORE_GOODS+"').click();");
			  System.out.println("More Goods Click executed");
			  Wait.untilPageLoadComplete(driver);		  
			  return true;
		}else {
			return false;
		}
	}
	public Boolean clickMoreGoods2() {
		List<WebElement> moreGoods = driver.findElements(moreGoodsLocator2);
		if(moreGoods!=null&&(!moreGoods.isEmpty())) {
			  js.executeScript("document.querySelector('"+MORE_GOODS2+"').scrollIntoView();document.querySelector('"+MORE_GOODS2+"').click();");
			  System.out.println("More Goods Click executed");
			  Wait.untilPageLoadComplete(driver);		  
			  return true;
		}else {
			return false;
		}
	}
	public List<String> searchElementsTitles(){
		List<WebElement> searchElements=Wait.waitUntilAllElementsAreVisible(driver, titleLocator);
		List<String> titles = new ArrayList<String>();
		for (int i=0 ; i<searchElements.size();i++) {
			titles.add(searchElements.get(i).getText().trim());
		}
		return titles;
	}
	
}
