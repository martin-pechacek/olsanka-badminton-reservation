package pages;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BadmintonReservationPage {

	private final WebDriver driver;
	private static WebDriverWait wait;
	
	@FindBy(id="schedule_0")
	static WebElement badmintonScheduleTable;
	
	@FindBy(id="scheduleNavigForm:view_filter_menu")
	static WebElement gridPeriodSelectBox;
	
	@FindBy(id="schedule_0")
	static WebElement firstTableInSchedule;
	
	@FindBy(xpath="//table[@id='schedule_0']/tbody/tr[2]/td[13]")
	static WebElement mondayCourt1Cell6PM;
	
	@FindBy(css="img[alt='rarr']")
	static WebElement navigateForwardArrow;
	
	@FindBy(className="bigWaiting")
	static WebElement waitingScreen;
	
	@FindBy(xpath="//*[@id='reservationForm:reservationEditFormStoreButton']")
	static WebElement reserveButton;
	
	@FindBy(xpath="//a[text()='Badminton']")
	static WebElement badmintonReservationsBtn;	

	
	/**
	 * Class constructor
	 * 
	 * @param driver - WebDriver
	 */
	public BadmintonReservationPage(WebDriver driver){
		this.driver = driver;
		BadmintonReservationPage.wait = new WebDriverWait(driver,10);
	}
	
	/**
	 * Method returning badminton schedule table object
	 * 
	 * @return badmintonScheduleTable - WebElement
	 */
	public WebElement getBadmintonScheduleTable(){
		return badmintonScheduleTable;
	}	
	
	/**
	 * Sets view of schedule grid
	 *
	 * @param period - eg. Týden (Week), Jeden den (One day), ...
	 * @throws InterruptedException 
	 */
	public void setScheduleView(String period) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(gridPeriodSelectBox));
		
		Select periodSelectBox = new Select(gridPeriodSelectBox);		
		periodSelectBox.selectByVisibleText(period);
		
		Thread.sleep(1500);
	}
	
	/**
	 * Returns first day in schedule
	 * 
	 * Eg. for Week view it is Po (Mo). 
	 * 
	 * @return  first day in schedule
	 */
	public String getFirstDayInSchedule() {
		String day = firstTableInSchedule.getText();
		
		return day;
	}
	
	/**
	 * Navigates forward in schedule
	 * 
	 * @param num - number of clicks on navigate forward arrow
	 * @throws InterruptedException 
	 */
	public void navigateForward(int num) throws InterruptedException{
		for(int i = 0; i<num;i++){	
			navigateForwardArrow.click();
			
			Thread.sleep(1500);
		}
	}
	
	/**
	 * Reserves court depending on parameters
	 * 
	 * @throws InterruptedException
	 * @params day - wanted day, eg. Thursday
	 * 		   hour - wanted hour, eg. 18:00
	 */
	public void reserveCourt(String day, String hour) throws InterruptedException{
		
		int dayNumber = getDayNumber(day);
		int hourNumber = getHourNumber(hour);
		
		for(int i=5; i>1; i--){
		    try {
				//Thursday, court 4(3,2,1 depends on i and if court is occupied), 18:00
    			WebElement cell = driver.findElement(getReservationTableRow(dayNumber, i, hourNumber));
				cell.click();
				
				Thread.sleep(1500);
				
				driver.switchTo().activeElement();
				
				reserveButton.click();
				
				Thread.sleep(2000);
				
				break;
		    } catch (org.openqa.selenium.WebDriverException e) {
		    	System.out.println(e.getMessage());
		    	System.out.println("=====================");
		    	System.out.println("Court occupied");	
		    	System.out.println("=====================");
		    }			
		}		
	}
	
	/**
	 * Method returning locator for cell in reservation table
	 * 
	 * @param table - number of reservation table (meant day)
	 * @param row -  number of row in reservation table (meant court)
	 * @param cell - number of cell in certain row (meant hour)
	 * @return reservationTableCell - locator for cell in reservation table 
	 */
	private By getReservationTableRow(int table, int row, int cell){
		By reservationTableRow = By.xpath("//table[@id='schedule_" + table + "']/tbody/tr["+ row +"]/td["+ cell +"]");
		
		return reservationTableRow;
	}
	
	/**
	 * Method returning dayNumber. Neccessary for correct reservation xpath 
	 * 
	 * @param day, eg. Monday
	 * @return dayNumber, eg. 0
	 */
	private int getDayNumber(String day){
		int dayNumber = 10000000;
		
		switch(day){
			case "Monday":
				dayNumber = 0;
				break;
			case "Tuesday":
				dayNumber = 1;
				break;
			case "Wednesday":
				dayNumber = 2;
				break;
			case "Thursday":
				dayNumber = 3;
				break;
			case "Friday":
				dayNumber = 4;
				break;
			case "Saturday":
				dayNumber = 5;
				break;
			case "Sunday":
				dayNumber = 6;
				break;
		}

		return dayNumber;
	}
	
	/**
	 * Method returning hourNumber. Neccessary for correct reservation xpath 
	 * 
	 * @param day, eg. 18:00
	 * @return dayNumber, eg. 13
	 */
	private int getHourNumber(String hour){		
		ArrayList<String> hoursList = new ArrayList<String>(Arrays.asList("6:00", "7:00", "8:00", "9:00", 
																  		  "10:00", "11:00", "12:00", "13:00",
																  		  "14:00", "15:00", "16:00", "17:00", 
																  		  "18:00", "19:00", "20:00", "21:00"));
		
		int hourNumber = hoursList.indexOf(hour);
		hourNumber = hourNumber+1;
		
		return hourNumber;
	}
}