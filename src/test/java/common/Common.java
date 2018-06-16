package common;

public class Common {
    
	private static final String CHROME_DRIVER_WIN = "src//test//resources//chromedriver.exe";
	private static final String CHROME_DRIVER_LINUX = "src//test//resources//chromedriver";
	
	/**
	 *  Method for setting system property - driver paths
	 */
	public static void commonTestSetup() {
		String system = System.getProperty("os.name");
		if(system.toLowerCase().contains("windows")){
	    	System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_WIN);
	    } else {
	    	//jenkins runs on linux server
			System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LINUX);
	    }
	}
}