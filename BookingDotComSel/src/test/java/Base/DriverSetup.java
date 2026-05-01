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
            FileInputStream ip = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/config.properties"
            );

            prop = new Properties();
            prop.load(ip);

            String browser = prop.getProperty("browser");

            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");

                driver = new ChromeDriver(options);
            } 
            else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");

                driver = new FirefoxDriver(options);
            } 
            else if (browser.equalsIgnoreCase("edge")) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");

                driver = new EdgeDriver(options);
            } 
            else {
                System.out.println("Browser not found!! Running in Chrome");

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");

                driver = new ChromeDriver(options);
            }

        } catch (Exception e) {
            System.out.println("File not found or driver setup failed: " + e.getMessage());
        }

        return driver;
    }

    public static void driverClose() {
        if (driver != null) {
            driver.quit();
        }
    }
}