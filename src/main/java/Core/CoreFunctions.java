package Core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CoreFunctions {
	
		static WebDriver driver;
		CoreFunctions(WebDriver driver){
		CoreFunctions.driver=driver;
		PageFactory.initElements(driver, this);
	}
		
		public static void click(WebElement Element, String name) {
			System.out.println("Clicking the Element" +name);
			try {
				Element.click();
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail();
			}
		}
		
		public static void setText(WebElement Element, String text) {
			System.out.println("setting the text" +text);
			try {
				Element.sendKeys(text);
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail();
			}
		}

	public synchronized static void WaitforBrowserToLoad() {
		try {
			boolean isReady = checkBrowserReadyState();
			if(isReady==false) {
				for(int i=0;i<90;i++) {
					try {
					Thread.sleep(1000);
					}catch(InterruptedException e) {
						
					}
					isReady=checkBrowserReadyState();
					if(isReady==true) {
						break;
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public synchronized static boolean checkBrowserReadyState() {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		if(js.executeScript("return document.readyState").toString().equals("complete")) {
			return true;
		}else {
			return false;
		}
		
	}
}
