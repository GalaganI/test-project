package tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
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

public class TestTaskTwo {
	
	 private SeleniumWebDriver selenium ;
	 private String searchedPhoneName = "Motorola Moto Z";
	 private FilterParametersForm filter ;
	 private MainMenu menu ;
	 private WebDriver driver;
	 private TelefonyTvIelektronikaCategories categories ; 
	 private TelefonySections sections ;
	 private SearchResults searchResults ;
	 private TopNavigationAndSearch topNavigation;
	
	@BeforeClass
	   public void beforeTestClass(){
	     selenium = new SeleniumWebDriver(Browsers.Chrome, "https://rozetka.com.ua/");
	     driver=selenium.getDriver();
	     driver.manage().window().maximize();
	     filter= new FilterParametersForm(driver,"producer");
	     menu = new MainMenu();
	     categories = new TelefonyTvIelektronikaCategories();
	     sections = new TelefonySections();
	     searchResults = new SearchResults(driver);
	     topNavigation = new TopNavigationAndSearch(driver);
	}
	
	@Test(description="test that searched element is finally found on any iteration.")
	public void testTopSearchIsFunctional() throws InterruptedException{
		String expectedTitle=null;
		boolean exists = false;
		//Press on «Смартфоны, ТВ и электроника».
		Wait.waitUntilElementIsVisible(driver,menu.getTvIElectronica()).click();
		//Press on «Телефоны».
		Wait.waitUntilElementIsVisible(driver,categories.getTelefony()).click();
		//Press on «Смартфоны».
		Wait.waitUntilElementIsVisible(driver,sections.getSmartfony()).click();
		//Choose “Производитель” - «Motorola».
		filter.selectOptionCheckBox("producer_22");
		//Save the full name of some element which contains “Motorola Moto Z”.
		selenium.wait(10);
		List<String> results = searchResults.searchElementsTitles();
		for(int i=0;i<results.size();i++) {
			if(results.get(i).contains(searchedPhoneName)){
				expectedTitle=results.get(i);
				break;
			}
		}
		System.out.println("Expected title:"+expectedTitle);
		//Go to https://rozetka.com.ua/.
		topNavigation.returneToHomePage();
		//Fill in “Motorola Moto Z” into the search field on the top of the page and press «Найти».
		topNavigation.search(searchedPhoneName);
		//On the search results page try to find the element with exactly the same name as the one you saved on step 6.
		List<String> actualResults = searchResults.searchElementsTitles();
		for(int j=0;j<actualResults.size();j++) {
			if(actualResults.get(j).contains(expectedTitle)){
				System.out.println("Actual title:"+actualResults.get(j));
				exists = true;
				break;
			}
			
		}
		while(searchResults.clickMoreGoods2()) {
			actualResults = searchResults.searchElementsTitles();
			for(int j=0;j<actualResults.size();j++) {
				if(actualResults.get(j).contains(expectedTitle)){
					System.out.println("Actual title2:"+actualResults.get(j));
					exists = true;
					break;
				}
				
			}
		}
		Assert.assertTrue(exists,"element could not be found and there is no button «Показать еще XX товаров» anymore.");
		
	}
	@AfterClass
	public void afterTestClass() {
		driver.close();
		driver.quit();
	}
	
	}
