package FlipkartPackage;

import org.testng.annotations.Test;
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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class FlipkartTest {
	
	static ChromeDriver driver = new ChromeDriver();
	static TakesScreenshot scrShot =((TakesScreenshot)driver);
	
	static void takess(ChromeDriver driver, String path) throws IOException {
		TakesScreenshot srcShot =((TakesScreenshot)driver);
		
		File SrcFile= srcShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(path);
		FileUtils.copyFile(SrcFile, DestFile);
		System.out.println("Screenshot taken!");
	}
	
	@BeforeTest
	public void setUpTest() {
		
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com");
		System.out.println("Browser Opened!");
		
	}
	
	@Test
	public void t0_CheckLoginTest() throws IOException {
		
		try {
			WebElement login_text = driver.findElement(By.className("_36KMOx")).findElement(By.tagName("span"));   
			
//			if(login_text.getText().equals("Login")) {
//				System.out.println("Login test passed!");
//			}else {
//				takess(driver, "Screenshots/login_test_failed.png");
//
//				System.out.println("Login test failed");
//			}
			
			assert login_text.getText().equals("Login");
			
		}catch(NoSuchElementException e) {
			System.out.println("Login Text Not found!");
			assert false;
		}

		
	}
	
	@Test
	public void t1_CheckUsernameField() throws IOException {
		
		try {
			
			WebElement username_field = driver.findElement(By.xpath("//span[text()='Enter Email/Mobile number']"));
			System.out.println("Username check passed");
			assert true;
			
		}catch(NoSuchElementException e) {
			takess(driver, "Screenshots/username_field_test_failed.png");
			System.out.println("Username Field not found!");
			assert false;
		}
	}
	
	@Test
	public void t2_CheckPasswordField() throws IOException {
		
		try {
			
			WebElement password_field = driver.findElement(By.xpath("//span[text()='Enter Password']"));
			System.out.println("Password check passed");
			assert true;
			
		}catch(NoSuchElementException e) {
			takess(driver, "Screenshots/password_field_test_failed.png");
			System.out.println("Username Field not found!");
			assert false;
		}
		
	}
	
	@Test
	public void t3_ClickCloseButton() {
		try {
			
			WebElement password_field = driver.findElement(By.xpath("//button[text()='✕']"));
			password_field.click();
			System.out.println("Close button closed!");
			assert true;
			
		}catch(NoSuchElementException e) {
			System.out.println("Close button not found!");
			assert false;
		}
	}
	
	@Test
	public void t4_VerifyTopCategories() throws IOException {
		
		try {
			
			WebElement top_categories_text = driver.findElement(By.className("_37M3Pb"));
			List<WebElement> top_categories_list = top_categories_text.findElements(By.xpath("//div/a/div[2]"));
			
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("Top Offers", 1);
			map.put("Mobiles", 1);
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
						assert false;
					}
				}
			}
			
			if(map.size() != 0) {
				System.out.println("Top categories check failed! Categories not found: " + map.keySet());
				takess(driver, "Screenshots/top_categories_check_failed.png");
				assert false;
			}
			
		}catch(NoSuchElementException e) {
			System.out.println("Top Categories Not Found!");
			assert false;
		}
		
	}
	
	@Test
	public void t5_ClickMobileCategory() {
		try {
			WebElement mobile_category = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[2]/a/div[2]"));
			mobile_category.click();
			System.out.println("Mobile Category clicked!");
			assert true;
		}catch(NoSuchElementException e) {
			System.out.println("Mobile Category not found!");
			assert false;
		}
	}
	
	@Test
	public void t6_ClickFirstPhone() {
		
		try {
			WebElement first_phone = driver.findElement(By.xpath("/html/body/div/div/div[3]/div[3]/div/div[1]"));
			first_phone.click();
			System.out.println("First phone clicked!");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			assert true;
		}catch(NoSuchElementException e) {
			System.out.println("First phone element not found!");
			assert false;
		}

		
	}
	
	@Test
	public void t7_CheckFilterTitle() {
		try {
			WebElement title_filter = driver.findElement(By.xpath("//section/div/div/span[text()='Filters']"));
			System.out.println("Fitler title check passed!");
			assert true;
		}catch(NoSuchElementException e) {
			System.out.println("Filter title not found!");
			assert false;
		}
	}
	
	@Test
	public void t8_set_price() {
		try {
			
			Select objSelect =new Select(driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div/div[1]/div/section[2]/div[4]/div[3]/select")));
			objSelect.selectByVisibleText("₹20000");
			System.out.println("Price field changed!");
			assert true;
			
		}catch(NoSuchElementException e) {
			System.out.println("Price field not found!");
			assert false;
		}
	}
	
	@Test
	public void t9_ScrollAndFindNeedHelp() {
		try {
			
			WebElement need_help = driver.findElement(By.xpath("//a[@title='Buying Guide']/div/span[text()='Need help?']"));
			
			Actions a = new Actions(driver);
			a.moveToElement(need_help);
		    a.perform();
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    
			
			System.out.println("Need help passed");
			assert true;
			
			
			
		}catch(NoSuchElementException e) {
			
			System.out.println("Need help failed!");
			assert false;
			
		}
	}
	
	@AfterTest
	public void tearDownTest() {
		
		driver.quit();
		
	}

}
