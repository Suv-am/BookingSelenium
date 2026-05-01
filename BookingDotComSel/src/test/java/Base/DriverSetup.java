package Base;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSetup {
	public static WebDriver driver;
	public static Properties prop;
	
	public static WebDriver getDriver() {
		try {
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		prop = new Properties();
		prop.load(ip);
		String browser = prop.getProperty("browser");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--window-size=1920,1080");
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		else {
			System.out.println("Browser not found!! Running in Chrome");
			driver = new ChromeDriver();
		}
		}
		catch(Exception e)
		{
			System.out.println("file not found!!");
		}
		driver.manage().window().maximize();
		return driver;
	}
	
	public static void driverClose() {
		driver.quit();
	}
}