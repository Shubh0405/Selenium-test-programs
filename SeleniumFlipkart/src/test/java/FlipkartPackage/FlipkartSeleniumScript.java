package FlipkartPackage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class FlipkartSeleniumScript {
	
	static void FlipkartStart() throws IOException {
		
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com");
		System.out.println("Browser Opened!");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		TakesScreenshot scrShot =((TakesScreenshot)driver);

		
//		1. Check Login text
		check_login_text(driver);
		
//		2. Check Username Field;
		check_username_field(driver);
		
//		3. Check Password Field:
		check_password_field(driver);
		
//		4. Click close button:
		click_close_button(driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
//		5. Check top categories:
		verify_top_categories(driver);
		
//		6. Click mobile category:
		click_mobile_category(driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
//		7. Click on any item eg: mobile
		click_first_phone(driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
//		8. verify title filter on left nav bar
		check_filter_title(driver);
		
//		9. update the price to 20000
		set_price(driver);
		
//		10. scroll down to the bottom of page and verify Need help?
		scroll_and_find_need_help(driver);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.quit();

  		
		
		
	}
	
	static void takess(ChromeDriver driver, String path) throws IOException {
		TakesScreenshot srcShot =((TakesScreenshot)driver);
		
		File SrcFile= srcShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(path);
		FileUtils.copyFile(SrcFile, DestFile);
		System.out.println("Screenshot taken!");
	}
	
	static void check_login_text(ChromeDriver driver) throws IOException {
		
		try {
			WebElement login_text = driver.findElement(By.className("_36KMOx")).findElement(By.tagName("span"));   
			
			if(login_text.getText().equals("Login")) {
				System.out.println("Login test passed!");
			}else {
				takess(driver, "Screenshots/login_test_failed.png");

				System.out.println("Login test failed");
			}
			
		}catch(NoSuchElementException e) {
			System.out.println("Login Text Not found!");
		}
		
		
	}
	
	static void check_username_field(ChromeDriver driver) throws IOException {
		
		try {
			
			WebElement username_field = driver.findElement(By.xpath("//span[text()='Enter Email/Mobile number']"));
			System.out.println("Username check passed");
			
		}catch(NoSuchElementException e) {
			takess(driver, "Screenshots/username_field_test_failed.png");
			System.out.println("Username Field not found!");
		}
		
	}
	
	static void check_password_field(ChromeDriver driver) throws IOException {
		
		try {
			
			WebElement password_field = driver.findElement(By.xpath("//span[text()='Enter Password']"));
			System.out.println("Password check passed");
			
		}catch(NoSuchElementException e) {
			takess(driver, "Screenshots/password_field_test_failed.png");
			System.out.println("Username Field not found!");
		}
		
	}
	
	static void click_close_button(ChromeDriver driver){
		
		try {
			
			WebElement password_field = driver.findElement(By.xpath("//button[text()='✕']"));
			password_field.click();
			System.out.println("Close button closed!");
			
		}catch(NoSuchElementException e) {
			System.out.println("Close button not found!");
		}
		
	}
	
	static void verify_top_categories(ChromeDriver driver) throws IOException {
		
		try {
			
			WebElement top_categories_text = driver.findElement(By.className("_37M3Pb"));
			List<WebElement> top_categories_list = top_categories_text.findElements(By.xpath("//div/a/div[2]"));
			
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("Top Offers", 1);
			map.put("Mobiles & Tablets", 1);
			map.put("Electronics", 1);
			map.put("Fashion", 1);
			map.put("Beauty", 1);
			map.put("Home & Furniture", 1);
			map.put("Appliances", 1);
			map.put("Travel", 1);
			map.put("Grocery", 1);
			
			for(WebElement e : top_categories_list) {
				if(!e.getText().equals("")) {
					if(map.containsKey(e.getText())) {
						map.remove(e.getText());
					}else {
						System.out.println("Top Categories Check failed! Extra Category found: " + e.getText());
						takess(driver, "Screenshots/top_categories_check_failed.png");
						return;
					}
				}
			}
			
			if(map.size() != 0) {
				System.out.println("Top categories check failed! Categories not found: " + map.keySet());
				takess(driver, "Screenshots/top_categories_check_failed.png");
				return;
			}
			
		}catch(NoSuchElementException e) {
			System.out.println("Top Categories Not Found!");
		}
		
	}
	
	static void click_mobile_category(ChromeDriver driver) {
		
		try {
			WebElement mobile_category = driver.findElement(By.className("_37M3Pb")).findElement(By.xpath("//div[2]/a/div[2][text()='Mobiles & Tablets']"));
			mobile_category.click();
			System.out.println("Mobile Category clicked!");
		}catch(NoSuchElementException e) {
			System.out.println("Mobile Category not found!");
		}
		
	}
	
	static void click_first_phone(ChromeDriver driver) {
		try {
			WebElement first_phone = driver.findElement(By.xpath("/html/body/div/div/div[3]/div[3]/div/div[1]"));
			first_phone.click();
			System.out.println("First phone clicked!");
		}catch(NoSuchElementException e) {
			System.out.println("First phone element not found!");
		}
	}
	
	static void check_filter_title(ChromeDriver driver) {
		
		try {
			WebElement title_filter = driver.findElement(By.xpath("//section/div/div/span[text()='Filters']"));
			System.out.println("Fitler title check passed!");
		}catch(NoSuchElementException e) {
			System.out.println("Filter title not found!");
		}
		
	}
	
	static void set_price(ChromeDriver driver) {
		try {
			
			Select objSelect =new Select(driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div/div[1]/div/section[2]/div[4]/div[3]/select")));
			objSelect.selectByVisibleText("₹20000");
			System.out.println("Price field changed!");
			
		}catch(NoSuchElementException e) {
			System.out.println("Price field not found!");
		}
	}
	
	static void scroll_and_find_need_help(ChromeDriver driver) {
		
		try {
			
			WebElement need_help = driver.findElement(By.xpath("//a[@title='Buying Guide']/div/span[text()='Need help?']"));
			
			Actions a = new Actions(driver);
			a.moveToElement(need_help);
		    a.perform();
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    
			
			System.out.println("Need help passed");
			
			
			
		}catch(NoSuchElementException e) {
			
			System.out.println("Need help failed!");
			
		}
		
	}
	

	
	public static void main(String args[]) throws IOException {
		FlipkartStart();
	}

}
