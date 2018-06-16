package seleniumTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Common;
import pages.BadmintonReservationPage;
import pages.OverviewPage;

public class ReservationTest {
	private static WebDriver driver;
    WebDriverWait wait;
	String DRIVER_PATH = "";
	private OverviewPage overviewPage;
	
	/**
	 * 
	 * Sets neccessary stuff for running test
	 * 
	 */
	@BeforeAll
	public static void setUpBeforeClass() {
		Common.commonTestSetup();
	}
	
	/**
	 * 
	 * Initialize chrome driver
	 * 
	 */
	@BeforeEach
	public void setUp() {
		String system = System.getProperty("os.name");
		if(system.toLowerCase().contains("windows")){
			driver = new ChromeDriver();
			driver.manage().window().maximize();
	    } else {
			//must be here, because of google-chrome bug(cannot maximalize window) on linux - workaround
//			ChromeOptions options = new ChromeOptions();
  //          options.addArguments("headless");
    //        options.addArguments("window-size=1024x768");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
	    }
	}
	
	/**
	 * 
	 * Closes browser after test
	 * 
	 */
	@AfterEach
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void ReserveCourt() throws InterruptedException {
		String username = "mpechacek";
		String password = "Smetak01";
		String scheduleView = "Týden";
		String day = "Thursday";
		String time = "18:00";
		
		overviewPage = PageFactory.initElements(driver, OverviewPage.class);	
		
		overviewPage.login(username, password);
		
		assertTrue(overviewPage.getMyReservationsBtn().isDisplayed(), "Login was invalid!");
		
		BadmintonReservationPage badmintonReservationPage = overviewPage.goToBadmintonReservations();
		
		badmintonReservationPage.setScheduleView(scheduleView);
		
		String firstDayInSchedule = badmintonReservationPage.getFirstDayInSchedule();
		
		assertTrue(firstDayInSchedule.contains("Po"), "For week view should be first day Monday but was " + firstDayInSchedule);
		
		badmintonReservationPage.navigateForward(3);
		
		badmintonReservationPage.reserveCourt(day, time);
		
		overviewPage.logout();
	}
	
/*	
	@BeforeTest
	public void setup() {
		
		wait = new WebDriverWait(driver,5);
		
		olsankaRezervace = new OlsankaRezervace();
		
		driver.get(olsankaRezervace.getUrl());
	}
	
	*/
}