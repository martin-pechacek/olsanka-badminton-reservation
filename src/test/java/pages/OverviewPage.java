package pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OverviewPage {
	
	
	private final WebDriver driver;
	private static WebDriverWait wait;
	
	String pageURL = "http://olsanka.e-rezervace.cz";
	
	@FindBy(id = "username")
	static WebElement usernameInput;
	
	@FindBy(id = "password")
	static WebElement passwordInput;

	@FindBy(css="input[value='Přihlásit']")
	static WebElement loginBtn;
	
	@FindBy(xpath="//a[text()='Badminton']/..")
	static WebElement badmintonReservationsBtn;
	
	@FindBy(css="input.logout-btn")
	static WebElement logoutBtn;
	
	@FindBy(css="a.my_reservations_menu")
	static WebElement myReservationsBtn;
	
/*	
	
	@FindBy(css="option[value='horizontal_service_week']")
	static WebElement weekGridTypeFromSelectBox;
	
	@FindBy(xpath="//div[@id='mp_reservationHeader']/div")
	static WebElement reservationModalTitle;
	

	
*/
	
	/**
	 * Class constructor
	 * 
	 * @param driver - WebDriver
	 */
	public OverviewPage(WebDriver driver){
		this.driver = driver;
		OverviewPage.wait = new WebDriverWait(driver,10);
		
		driver.get(pageURL);
	}
	
	/**
	 * Method for logging in
	 * 
	 * @param username - String
	 * @param password - String
	 */
	public void login(String username, String password) {	
		wait.until(ExpectedConditions.visibilityOf(usernameInput));
		
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginBtn.click();
	}
	
	/**
	 * Method for logging out
	 * 
	 */
	public void logout() {
		logoutBtn.click();
	}
	
	/**
	 * Method returning object My Reservations button
	 * 
	 * @return myReservationsBtn - WebElement
	 */
	public WebElement getMyReservationsBtn(){
		return myReservationsBtn;
	}
	
	/**
	 * Method switching page to badminton reservations
	 * 
	 * @param email - String
	 * @param password - String
	 * @return BadmintonReservationPage - object of BadmintonReservationPage class
	 * @throws InterruptedException 
	 */
	public BadmintonReservationPage goToBadmintonReservations() throws InterruptedException {
		boolean run = true;
		
		wait.until(ExpectedConditions.elementToBeClickable(badmintonReservationsBtn));
		
		badmintonReservationsBtn.click();
		
		//workaround for stale element reference: element is not attached to the page document error
		while(run) {
			try {	
				wait.until(ExpectedConditions.attributeToBe(badmintonReservationsBtn, "background-color", "rgba(255, 155, 0, 1)"));
	            run = false;
			} catch(StaleElementReferenceException e) {
				run = true;
	        }
		}
		
		return PageFactory.initElements(driver, BadmintonReservationPage.class);
	}
}