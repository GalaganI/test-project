package menuObjects;

import org.openqa.selenium.By;

public class TelefonySections {
	public static final String SMARTFONY = "a[href$=\"preset=smartfon/\"]";
	private By smartfonyLocator = By.cssSelector(SMARTFONY);
	
	public By getSmartfony() {
		return smartfonyLocator;
	}
}
