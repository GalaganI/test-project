package menuObjects;

import org.openqa.selenium.By;

public class TelefonyTvIelektronikaCategories {
	public static final String TELEFONY = "a[href=\"https://rozetka.com.ua/telefony/c4627900/\"]";
	private By telefonyLocator = By.cssSelector(TELEFONY);
	
	
	public By getTelefony() {
		return telefonyLocator;
	}
}
