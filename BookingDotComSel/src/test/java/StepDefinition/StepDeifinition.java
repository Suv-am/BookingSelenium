package StepDefinition;

import org.openqa.selenium.WebDriver;

import Base.DriverSetup;
import PageObjects.FlightPage;
import PageObjects.HomePage;
import Utils.Screenshots;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDeifinition {
	public static WebDriver driver;
	
	
	@Given("User clicks on Flights button in the home page")
	public void user_clicks_on_flights_button_in_the_home_page() {
	    driver = DriverSetup.getDriver();
	    driver.get("https://www.booking.com/");
	    HomePage home = new HomePage(driver);
	    Screenshots.takeScreenshot(driver);
	    home.flightPage();
	}

	@When("^User enters from location as (.+) to location as (.+) for any date$")
	public void user_enters_from_location_as_departure_to_location_as_arrival_for_any_date(String dept, String ar) {
		FlightPage fp = new FlightPage(driver);
	    fp.searchLocations(dept,ar);
	    Screenshots.takeScreenshot(driver);
	}

	@When("^with (\\d+) and (\\d+)$")
	public void with_adult_and_child(int adult, int child) {
		FlightPage fp = new FlightPage(driver);
		Screenshots.takeScreenshot(driver);
		fp.passengerSelection(adult,child);
	}

	@Then("User will be navigated to flight options page")
	public void user_will_be_navigated_to_flight_options_page() {
		FlightPage fp = new FlightPage(driver);
		fp.menuPage();
		Screenshots.takeScreenshot(driver);
	    DriverSetup.driverClose();
	}

}
