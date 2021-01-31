package Pages;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import Core.CoreFunctions;

public class HomePage {

			static WebDriver driver;
			public HomePage(WebDriver driver){
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
			
		@FindBy(id = "language")
		public static WebElement languageDropdown;
		
		@FindBy(css="a.ui-select-choices-row-inner")
		public static List <WebElement> languageOptions;
		
		@FindBy(css="h2.ng-scope")
		public WebElement txtCreateAccount;
		
		@FindBy(xpath="//div[@class='form-container']/center")
		public WebElement txtOnceCreated;
		
		@FindBy(id = "name")
		public WebElement inputName;
		
		@FindBy(id = "orgName")
		public WebElement inputOrg;
		
		@FindBy(id = "singUpEmail")
		public WebElement inputEmail;
		
		@FindBy(xpath="//input[@ng-model='signUp.agree']")
		public WebElement inputAgree;
		
		@FindBy(xpath="//label[@class='ui-checkbox']/span")
		public WebElement txtAgree;
		
		@FindBy(xpath="//label[@class='ui-checkbox']/a")
		public WebElement txtTerms;
		
		@FindBy(css="button.btn")
		public WebElement btnGetStarted;
		
		@FindBy(css="p.text-center")
		public WebElement txtAlreadyHave;
		
		@FindBy(css="a.text-custom")
		public WebElement txtLogin;
		
		@FindBy(css="div.alert>span")
		public WebElement msgAlert;
		
		
		public void VerifyLangaugeOptions()
		{	
			List<String> language = new ArrayList <String>();
			language.add("English");
			language.add("Dutch");
			CoreFunctions.click(languageDropdown,"Language dropdown");
			TreeMap<String, Object[]>LanguageLabels=new TreeMap<String, Object[]>();
			LanguageLabels.put("Language1", new Object[]{languageOptions.get(0), language.get(0)});
			LanguageLabels.put("Language2", new Object[]{languageOptions.get(1), language.get(1)});
			
			for(Entry<String, Object[]> entry:LanguageLabels.entrySet()) {
				Object[] arr=entry.getValue();
				VerifyOptions((WebElement)arr[0], (String)arr[1]);
				
			}
			
			CoreFunctions.click(languageOptions.get(0), "English");

		}

		private static void VerifyOptions(WebElement ActLang, String ExpLang) {
			Assert.assertTrue(ActLang.getText().equals(ExpLang));
			
		}

		public void VerifyFormSubmit() {
			CoreFunctions.setText(inputName,"Rishi");
			CoreFunctions.setText(inputOrg,"Rishi ORG");
			CoreFunctions.setText(inputEmail, "rishi.kumar@org.com");
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", inputAgree);
			
			CoreFunctions.click(btnGetStarted,"Get Started button");
			WebDriverWait wait=new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(msgAlert));
			Assert.assertTrue(msgAlert.getText().equalsIgnoreCase("A welcome email has been sent. Please check your email."));
			
		}
}
	
