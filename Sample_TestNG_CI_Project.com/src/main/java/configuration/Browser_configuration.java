package configuration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class Browser_configuration extends ExtentReportsClass {
	static String driverPath = "D://Public//z015055//Automation tools//Selenium//chromedriver_win32//chromedriver.exe";
	public static WebDriver driver;

	@BeforeSuite
	public void setUp() {
		System.out.println("*******************");
		System.out.println("launching chrome browser");
		System.setProperty("webdriver.chrome.driver", driverPath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("disable-infobars");
		driver = new ChromeDriver(options);

	}

	@Test(priority = 0)
	public void accountActivationYopmail() {
		try{
			Thread.sleep(3000);
			driver.get("https://www.renault.co.in/");
			//Thread.sleep(5000);
			runtimeProcess = Runtime.getRuntime().exec("cmd /c start cmd.exe /C\""D:\\Public\\z015055\\AWS\\End to End SampleProject\\login.exe"");R
			int processComplete = runtimeProcess.waitFor();
			Thread.sleep(5000);
			String strPageTitle = driver.getTitle();
			System.out.println("Page title: - "+strPageTitle);
			Assert.assertTrue(strPageTitle.equalsIgnoreCase("Yopmail"), "Page title doesn't match");
			//Assert.assertTrue(false);
			logger.log(LogStatus.valueOf(strPageTitle), "Test Case Status is passed");

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void tearDown() {
		if(driver!=null) {
			System.out.println("Closing chrome browser");
			driver.quit();
		}
	}
}
