package PageObjects;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FlightPage {
	 WebDriver driver;
	 public FlightPage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	@FindBy(xpath="//input[@value='ONEWAY']")
	WebElement oneWayRadio;
	@FindBy(xpath="//select[@aria-label='Cabin class']")
	WebElement cabinClass;
	@FindBy(xpath="//button[@data-ui-name='input_location_from_segment_0']")
	WebElement fromLocation;
	@FindBy(xpath="//input[@aria-controls='flights-searchbox_suggestions']")
	WebElement deptInputBox;
	@FindBy(xpath="//button[@data-ui-name='input_location_to_segment_0']")
	WebElement toLocation;
	@FindBy(xpath="//input[@data-ui-name='input_text_autocomplete']")
	WebElement arInputBox;
	@FindBy(xpath="//div[text()='Select multiple airports at once or select \"Anywhere\" to explore']/following-sibling::div[1]//button")
	WebElement anywhereButton;
	@FindBy(xpath="//button[@data-ui-name='input_location_to_segment_0']//span[2]")
	WebElement arvLocation;
	@FindBy(xpath="//button[@data-ui-name='button_date_segment_0']")
	WebElement travelDate;
	@FindBy(xpath="//button[@aria-label='Next month']")
	WebElement nextMonth;
	@FindBy(xpath="//button[@data-ui-name='button_occupancy']")
	WebElement travelers;
	By occupancyDropdown = By.xpath("//div[starts-with(@class,'OccupancyDropDown-module')]");
	@FindBy(xpath="//button[@data-ui-name='button_occupancy_adults_minus']/following-sibling::span")
	WebElement initialAdultCount;
	@FindBy(xpath="//button[@data-ui-name='button_occupancy_adults_plus']")
	WebElement adultIncrease;
	@FindBy(xpath="//button[@data-ui-name='button_occupancy_adults_minus']")
	WebElement adultDecrease;
	@FindBy(xpath="//button[@data-ui-name='button_occupancy_children_minus']/following-sibling::span")
	WebElement initialChildCount;
	@FindBy(xpath="//button[@data-ui-name='button_occupancy_children_plus']")
	WebElement childIncrease;
	@FindBy(xpath="//button[@data-ui-name='button_occupancy_children_minus']")
	WebElement childDecrease;
	@FindBy(xpath="//button[@data-ui-name='button_occupancy_action_bar_done']")
	WebElement occupancyDone;
	@FindBy(xpath="//button[@data-ui-name='button_search_submit']")
	WebElement submitSearchButton;
	
	
	public void searchLocations(String departure, String arrival) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'flights with ease')]")));
		oneWayRadio.click();
		Select select = new Select(cabinClass);
		select.selectByValue("BUSINESS");
		fromLocation.click();
		deptInputBox.sendKeys(departure,Keys.ENTER);
		toLocation.click();
		arInputBox.sendKeys(arrival,Keys.ENTER);
		if((arvLocation.getText()).contains("Going to")) {
			anywhereButton.click();
		}
		travelDate.click();
		try {
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		Properties prop=new Properties();
		prop.load(ip);
		String date = prop.getProperty("travel_date");
		By dateLocator = By.xpath("//span[@data-date='" + date + "']");
		int attempts = 0;
		while(attempts<12) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(dateLocator)).click();
				break;
			}
			catch(Exception e) {
				nextMonth.click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		        attempts++;
			}
		}
		}
		catch(Exception e) {
			System.out.println("Date selection is not successful");
			 e.printStackTrace();
		}
	}
	
	public void passengerSelection(int ad, int ch) {
		travelers.click();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(occupancyDropdown));

	    int defaultAdult = Integer.parseInt(initialAdultCount.getText().trim());
	    int defaultChild = Integer.parseInt(initialChildCount.getText().trim());
	    try {
	    while (defaultAdult < ad) {
	        adultIncrease.click();
	        Thread.sleep(3000);
	        defaultAdult=Integer.parseInt(initialAdultCount.getText().trim());
	    }

	    while (defaultAdult > ad) {
	        adultDecrease.click();
	        Thread.sleep(3000);
	        defaultAdult=Integer.parseInt(initialAdultCount.getText().trim());
	    }

	    while (defaultChild < ch) {
	        childIncrease.click();
	        Thread.sleep(3000);
	        defaultChild= Integer.parseInt(initialChildCount.getText().trim());
	    }

	    while (defaultChild > ch) {
	        childDecrease.click();
	        Thread.sleep(3000);
	        defaultChild= Integer.parseInt(initialChildCount.getText().trim());
	    }
	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    }
	    int i=0;
	    List<WebElement> childAge = driver.findElements(By.xpath("//select[starts-with(@class,'InputSelect-module')]"));
	    while(i<=ch-1) {
	    	Select select = new Select(childAge.get(i));
	    	select.selectByValue("4");
	    	i++;
	    }
	    occupancyDone.click();
	    submitSearchButton.click();
	}
	
	public void menuPage() {
		Assert.assertTrue(driver.getCurrentUrl().contains("fly-anywhere"));
	}
	
}