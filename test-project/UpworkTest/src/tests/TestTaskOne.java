package tests;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import menuObjects.FilterParametersForm;
import menuObjects.MainMenu;
import menuObjects.TelefonySections;
import menuObjects.TelefonyTvIelektronikaCategories;
import searchObjects.SearchResults;
import searchObjects.TopNavigationAndSearch;
import utils.SeleniumWebDriver;
import utils.SeleniumWebDriver.Browsers;
import utils.Wait;

public class TestTaskOne {

	 private SeleniumWebDriver selenium ;
	 private String searchedPhoneName = "Motorola Moto Z";
	 private FilterParametersForm filter ;
	 private MainMenu menu ;
	 private WebDriver driver;
	 private TelefonyTvIelektronikaCategories categories ; 
	 private TelefonySections sections ;
	 private SearchResults searchResults ;
	 private TopNavigationAndSearch topNavigation;
	 private String expectedTag = "novelty";
	
	@BeforeClass
	   public void beforeTestClass(){
	     selenium = new SeleniumWebDriver(Browsers.Chrome, "https://rozetka.com.ua/");
	     driver=selenium.getDriver();
	     driver.manage().window().maximize();
	     filter= new FilterParametersForm(driver,"state");
	     menu = new MainMenu();
	     categories = new TelefonyTvIelektronikaCategories();
	     sections = new TelefonySections();
	     searchResults = new SearchResults(driver);
	     topNavigation = new TopNavigationAndSearch(driver);
	}
	@Test(description="Test if the first element has a label \"new\" and all \"new\" items on the page appear next to each other, while all items without such label are further down.")
		public void testFilteredElementsHaveCorrectTag() throws InterruptedException {
				boolean exists = false;
		        //Press on «Смартфоны, ТВ и электроника».
				Wait.waitUntilElementIsVisible(driver,menu.getTvIElectronica()).click();
				//Press on «Телефоны».
				Wait.waitUntilElementIsVisible(driver,categories.getTelefony()).click();
				//Press on «Смартфоны».
				Wait.waitUntilElementIsVisible(driver,sections.getSmartfony()).click();
				//Choose sorting by «новинки».
				filter.selectOptionCheckBox("state_1");
				//Verify that the first element on the page is visually labeled as "новинка" (“new”).
				selenium.wait(10);
				List<WebElement> tags = searchResults.returnAllSearchResultTags();
				String tagsAttribute = tags.get(0).getAttribute("class");
				Assert.assertTrue(tagsAttribute.contains(expectedTag),"First element on the page is not visually labeled as \"новинка\" (“new”)");
				//Find first element on the page, which does NOT have a label “new”, and in case if such element is found - verify that there are no elements with a label “new” after this one.
				for(int i=0 ; i<tags.size();i++) {
					if(!tags.get(i).getAttribute("class").contains(expectedTag)) {
						for(int j=i++ ; j<tags.size();j++) {
						   if(tags.get(j).getAttribute("class").contains(expectedTag)) {
							   exists=true;
						   }
						}
					}
				}
				while(searchResults.clickMoreGoods()) {
					tags = searchResults.returnAllSearchResultTags();
					for(int j=0; j<tags.size();j++) {
						   if(tags.get(j).getAttribute("class").contains(expectedTag)) {
							   exists=true;
						   }
						}
				}
				//Assert that no more lements with tag new found
				Assert.assertFalse(exists, "Was found element with tag new");
		}
	@AfterClass
		public void afterTestClass() {
			driver.close();
			driver.quit();
		}
}
