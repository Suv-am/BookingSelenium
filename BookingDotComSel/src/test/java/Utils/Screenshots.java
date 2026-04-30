package Utils;

import java.io.File;

import org.apache.maven.surefire.shared.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {
	public static void takeScreenshot(WebDriver driver) {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File des = new File("target/"+"screeshot_"+System.currentTimeMillis()+".png");
		try {
			FileUtils.copyFile(src, des);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
