package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OlsankaRezervace {
	WebDriver driver;
	private static String URL = "http://olsanka.e-rezervace.cz";
	By usernameInput = By.id("username");
	By passwordInput = By.id("password");
	By loginBtn = By.cssSelector("input[value='Pøihlásit']");
	By gridTypeSelectBox = By.id("scheduleNavigForm");
	By weekGridTypeFromSelectBox = By.cssSelector("option[value='horizontal_service_week']");
	By logoutBtn = By.cssSelector("input[id='logged_user_header_form:j_id202']");
	By reservationModalTitle = By.xpath("//div[@id='mp_reservationHeader']/div");
	By nextWeekArrow = By.cssSelector("img[alt='rarr']");
	By reserveButton = By.xpath("//*[@id='reservationForm:reservationEditFormStoreButton']");
	
	/**
	 * Class constructor
	 * 
	 * @param driver - WebDriver
	 */
	public OlsankaRezervace(){
		
	}
	
	/**
	 * Method returning URL
	 * 
	 * @return URL - url for webpage(Olsanka E-Rezervace)
	 */
	public String getUrl(){
		return URL;
	}
	
	/**
	 * Method returning locator for username input field
	 * 
	 * @return usernameInput - username input field in login form
	 */
	public By getUsernameInput(){
		return usernameInput;
	}
	
	/**
	 * Method returning locator for password input field
	 * 
	 * @return passwordInput - password input field in login form
	 */
	public By getPasswordInput(){
		return passwordInput;
	}
	
	/**
	 * Method returning locator for login button
	 * 
	 * @return loginBtn - login button in login form
	 */
	public By getLoginButton(){
		return loginBtn;
	}
	
	/**
	 * Method returning locator for logout button
	 * 
	 * @return logoutBtn - logout button in login form
	 */
	public By getLogoutButton(){
		return logoutBtn;
	}
	
	/**
	 * Method returning locator for selectbox with 
	 * different types of reservation grid
	 * 
	 * @return gridTypeSelectBox - selectbox for reservation grid type 
	 */
	public By getGridTypeSelectBox(){
		return gridTypeSelectBox;
	}
	
	/**
	 * Method returning locator for weekly grid option of reservation page 
	 * in selectbox
	 * 
	 * @return weekGridTypeFromSelectBox - weekly option in selectbox for reservation grid type 
	 */
	public By getWeekGridTypeFromSelectBox(){
		return weekGridTypeFromSelectBox;
	}
	
	/**
	 * Method returning locator for cell in reservation table
	 * 
	 * @param table - number of reservation table (meant day)
	 * @param row -  number of row in reservation table (meant court)
	 * @param cell - number of cell in certain row (meant hour)
	 * @return reservationTableCell - locator for cell in reservation table 
	 */
	public By getReservationTableRow(int table, int row, int cell){
		By reservationTableRow = By.xpath("//table[@id='schedule_" + table + "']/tbody/tr["+ row +"]/td["+ cell +"]");
		
		return reservationTableRow;
	}
	
	/**
	 * Method returning reservation modal title
	 * 
	 * @return reservationModalTitle - xpath of reservation modal title
	 */
	public By getReservationModalTitle(){
		return reservationModalTitle;
	}
	
	/**
	 * Method returning css selector for navigation arrow (Next Week)
	 * 
	 * @return nextWeekArrow - xpath of arrow for navigating to next week
	 */
	public By getNextWeekArrow(){
		return nextWeekArrow;
	}
	
	/**
	 * Method returning id for reserve button
	 * 
	 * @return nextWeekArrow - id of afor reserve button
	 */
	public By getReserveButton(){
		return reserveButton;
	}
}
