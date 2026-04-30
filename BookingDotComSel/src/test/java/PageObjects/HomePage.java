package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	 WebDriver driver;
	 public HomePage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	@FindBy(xpath="//button[@aria-label='Dismiss sign-in info.']")
    WebElement popupCrossButton;
	@FindBy(xpath="//a[@id='flights']")
	WebElement flightButton;
	
	public void flightPage() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		if(popupCrossButton.isDisplayed()) {
			popupCrossButton.click();
		}
		flightButton.click();
	}

}
