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
    WebDriverWait wait;
	String DRIVER_PATH = "";
	private static final String CHROME_DRIVER_WIN = "src//utility//chromedriver.exe";
	private static final String CHROME_DRIVER_LINUX = "//usr//local//bin//chromedriver";
	private OlsankaRezervace olsankaRezervace;
	
	
	@BeforeTest
	public void setup() {
		String system = System.getProperty("os.name");
		if(system.toLowerCase().contains("windows")){
	    	System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_WIN);
			//must be here, because of google-chrome bug(cannot maximalize window) on linux - workaround
			driver = new ChromeDriver();
			driver.manage().window().maximize();
	    } else {
			driver = new FirefoxDriver();
	    	System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LINUX);
			//must be here, because of google-chrome bug(cannot maximalize window) on linux - workaround
			
	    }		
		wait = new WebDriverWait(driver,5);
		
		olsankaRezervace = new OlsankaRezervace();
		
		driver.get(olsankaRezervace.getUrl());
	}
	
	@Test(priority=0)
	public void login() throws InterruptedException {
		WebElement usernameInput = driver.findElement(olsankaRezervace.getUsernameInput()); 
		usernameInput.sendKeys("mpechacek");
	 
		WebElement passwordInput = driver.findElement(olsankaRezervace.getPasswordInput());
		passwordInput.sendKeys("Smetak01");
	 
		WebElement submitBtn = driver.findElement(olsankaRezervace.getLoginButton());
		submitBtn.click();	 
		
	    wait.until(ExpectedConditions.visibilityOfElementLocated(olsankaRezervace.getLogoutButton()));
		
		Thread.sleep(5000);
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
		
		for(int i=5; i>1; i--){
		    try {
				//Thursday, court 4(3,2,1 depends on i and if court is occupied), 18:00
  //   			WebElement cell = driver.findElement(olsankaRezervace.getReservationTableRow(3, i, 13));
				//Thursday, court 4(3,2,1 depends on i and if court is occupied), 18:00
     			WebElement cell = driver.findElement(olsankaRezervace.getReservationTableRow(4, i, 13));
				cell.click();
				
				Thread.sleep(2000);
				
				driver.switchTo().activeElement();
		    	
		    	WebElement reserveButton = driver.findElement(olsankaRezervace.getReserveButton());
				reserveButton.click();
				
				Thread.sleep(2000);
				
				break;
		    } catch (org.openqa.selenium.WebDriverException e) {
		    	System.out.println(e.getMessage());
		    	System.out.println("Court occupied");	
		    }			
		}		
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
