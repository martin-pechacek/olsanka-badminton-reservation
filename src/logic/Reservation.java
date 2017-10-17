package logic;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.OlsankaRezervace;


public class Reservation {
    WebDriver driver;
	String DRIVER_PATH = "";
	private static final String CHROME_DRIVER_WIN = "src//utility//chromedriver.exe";
	private static final String CHROME_DRIVER_LINUX = "//usr//local//bin//chromedriver";
	private OlsankaRezervace olsankaRezervace;
	
	
	@BeforeTest
	public void setup() {
		String system = System.getProperty("os.name");
		if(system.toLowerCase().contains("windows")){
	    	System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_WIN);
	    } else {
	    	System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LINUX);
	    }
		
//		driver = new ChromeDriver();
		
		driver = new FirefoxDriver();
		
		olsankaRezervace = new OlsankaRezervace();
		
		driver.get(olsankaRezervace.getUrl());
		driver.manage().window().maximize();
	}
	
	@Test(priority=0)
	public void login() {
		WebElement usernameInput = driver.findElement(olsankaRezervace.getUsernameInput()); 
		usernameInput.sendKeys("mpechacek");
	 
		WebElement passwordInput = driver.findElement(olsankaRezervace.getPasswordInput());
		passwordInput.sendKeys("Smetak01");
	 
		WebElement submitBtn = driver.findElement(olsankaRezervace.getLoginButton());
		submitBtn.click();	 
	}
	
	@Test(priority=1)
	public void getWeeklyGrid(){
		WebElement gridTypeSelectBox = driver.findElement(olsankaRezervace.getGridTypeSelectBox());
		gridTypeSelectBox.click();
		
		WebElement weekGridTypeFromSelectBox = driver.findElement(olsankaRezervace.getWeekGridTypeFromSelectBox());
		weekGridTypeFromSelectBox.click();
	}
	
	@Test(priority=2)
	public void findFreeCourt() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='schedule_0']/tbody/tr[2]/td[13]")));
		
		for(int i = 0; i<3;i++){	
			WebElement nextWeekArrow = driver.findElement(olsankaRezervace.getNextWeekArrow());
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", nextWeekArrow);
			
			Thread.sleep(3000);
		}
		
		reserveCourt();
		
	}
	
	private void reserveCourt() throws InterruptedException{
		//thursday, court 4, 19	:00
		//WebElement cell = driver.findElement(olsankaRezervace.getReservationTableRow(2, 5, 14));
		WebElement cell = driver.findElement(olsankaRezervace.getReservationTableRow(1, 5, 16));
		cell.click();
		
		Thread.sleep(2000);
		
		driver.switchTo().activeElement();
		WebElement reserveButton = driver.findElement(olsankaRezervace.getReserveButton());
		reserveButton.click();
		
		Thread.sleep(2000);
	}
	
	@Test(priority=3)
	public void logout(){
		WebElement logoutBtn = driver.findElement(olsankaRezervace.getLogoutButton());
		logoutBtn.click();
	}
	
	@AfterTest
	public void teardown() {
		driver.close();
	}
}
