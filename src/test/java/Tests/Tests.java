package Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import Pages.HomePage;
import Core.CoreFunctions;

public class Tests {
	
	public static WebDriver driver;
	static String url = "http://jt-dev.azurewebsites.net/#/SignUp";
	static String key = "webdriver.chrome.driver";
	static String driverPath = System.getProperty("user.dir")+"//Drivers//chromedriver";
	static HomePage HomePage;
	
	@BeforeTest
	public void setup() {
		System.setProperty(key, driverPath);
		driver = new ChromeDriver();
		System.out.println("launching Chrome browser");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(url);
		
	}
	
	@Test
	public static void Test_Case_1() {
		HomePage = new HomePage(driver);
		CoreFunctions.WaitforBrowserToLoad();
		Assert.assertEquals(true, HomePage.languageDropdown.isDisplayed());
		HomePage.VerifyLangaugeOptions();
		Assert.assertEquals(true, HomePage.txtCreateAccount.isDisplayed());
		Assert.assertTrue(HomePage.txtCreateAccount.getText().equalsIgnoreCase("Create Your Account"));
		Assert.assertEquals(true, HomePage.txtOnceCreated.isDisplayed());
		Assert.assertTrue(HomePage.txtOnceCreated.getText().equalsIgnoreCase("Once created, we’ll walk you through the setup!"));
		Assert.assertEquals(true, HomePage.inputName.isDisplayed());
		Assert.assertEquals(true, HomePage.inputOrg.isDisplayed());
		Assert.assertEquals(true, HomePage.inputEmail.isDisplayed());
		Assert.assertEquals(true, HomePage.txtAgree.isDisplayed());
		Assert.assertTrue(HomePage.txtAgree.getText().equalsIgnoreCase("I agree to the"));
		Assert.assertEquals(true, HomePage.txtTerms.isDisplayed());
		Assert.assertTrue(HomePage.txtTerms.getText().equalsIgnoreCase("Terms and Conditions"));		
		Assert.assertEquals(true, HomePage.btnGetStarted.isDisplayed());
		Assert.assertTrue(HomePage.btnGetStarted.getText().equalsIgnoreCase("Get Started"));
		Assert.assertEquals(true, HomePage.txtAlreadyHave.isDisplayed());
		Assert.assertTrue(HomePage.txtAlreadyHave.getText().equalsIgnoreCase("Already have an account? Login"));		
		Assert.assertEquals(true, HomePage.txtLogin.isDisplayed());
		Assert.assertTrue(HomePage.txtLogin.getText().equalsIgnoreCase("Login"));
		HomePage.VerifyFormSubmit();
		
	}
	
	@AfterMethod
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
