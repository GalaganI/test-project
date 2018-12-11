package menuObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Wait;

public class FilterParametersForm {
	
	private static final String FORM_ID = "filter_parameters_form";
	private static final String PARAM_BLOCK = "#"+FORM_ID+">div[param=\"%s\"]";
	private static final String FILTER_OPTION_CHECKBOX = "a[name=\"%s\"]"; 
	private WebDriver driver ;
	private String param ; 
	private By block ;
	
	
	public FilterParametersForm(WebDriver driver , String param ){
		this.driver = driver;
		this.param = param ; 
		block = By.cssSelector(String.format(PARAM_BLOCK, param));
	}
	public Boolean isExpanded() {
		WebElement element = driver.findElement(block);
		String attribute = element.getAttribute("class");
		if(attribute.contains("hidden")) {
			return false;
		}else {
			return true;
			}
		
	}
	public void expand() {
		if(!isExpanded()) {
			WebElement element = driver.findElement(block);
			element.findElement(By.cssSelector("a[href=\"#\"]")).click();
		}
		
	}
	public void selectOptionCheckBox(String name) {
		if(!isExpanded()) {
			expand();
		}
		WebElement filterBlock = Wait.waitUntilElementPresent(driver, block);
		filterBlock.findElement(By.cssSelector(String.format(FILTER_OPTION_CHECKBOX, name))).click();
		Wait.untilPageLoadComplete(driver);
	}
}
