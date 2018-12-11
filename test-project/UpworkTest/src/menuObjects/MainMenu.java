package menuObjects;

import org.openqa.selenium.By;


public class MainMenu {
	
	public static final String TELEFONY_TV_I_ELEKTRONIKA = "li[id=\"3361\"]>a";
	private By tvIElectonicaLocator = By.cssSelector(TELEFONY_TV_I_ELEKTRONIKA);
	
	
	public By getTvIElectronica() {
		return tvIElectonicaLocator;
	}

}
