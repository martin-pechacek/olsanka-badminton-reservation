# Olsanka badminton - Reservation
Application for badminton court reservation in [Sportovní centrum Olšanka](http://olsanka.e-rezervace.cz) built on [Selenium WebDriver](http://www.seleniumhq.org/) and [JUnit 5](https://junit.org/junit5/)

## Installation

### Prerequisites for developing
- Install IDE, eg. [Eclipse](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/oxygen/R/eclipse-inst-win64.exe)
- Install [Google Chrome](https://www.google.com/chrome/)
- Download [Chrome Driver](https://sites.google.com/a/chromium.org/chromedriver/downloads) unzip to folder and set the folder's path in your system path
- (For Linux users: put chromedriver file to usr/local/bin)
- Download and install [Java Development Kit (JDK)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
- Create new project in your IDE and set JUnit 5 library
- Clone git repo to your project
- Download [Selenium Java Driver](http://seleniumhq.org/download/) unzip to folder and put it in your project's classpath
- Download Maven plugin - [Follow instructions](http://toolsqa.com/java/maven/how-to-install-maven-eclipse-ide/)

### Prerequisities for running tests from command line
- Download and set [Maven](https://www.mkyong.com/maven/how-to-install-maven-in-windows/)

## Usage
**1. Set your reservation (test) parameters:**
- username = your login username
- password = your login password
- scheduleView = type of schedule grid view (Týdnì (Weekly), Jeden den (One day), etc.)
- day = Day you want to reserve court on, eg. Thursday
- time = Time in 24h format you want to reserve court on, eg. 18:00
- weeksAhead = how many weeks ahead you want to reserve court (0 is actual week)

  **There are three possibilities how to set parameters:**

  ***1.1. Maven command for running test***
  ```{r, engine='sh'}
  mvn -Dreservation.username=\YOUR_USERNAME\ 
      -Dreservation.password=\YOUR_PASSWORD\ 
      -Dreservation.scheduleView=\SCHEDULE_VIEW\ 
      -Dreservation.day=\DAY\ -Dreservation.time=\TIME\ 
      -Dreservation.weeksAhead=\WEEKS_AHEAD\
      clean test
  ```

  ***1.2. Directly in _POM.xml_ file***
  ```{r, engine='sh'}
  <systemProperties>
    <reservation.username>\YOUR_USERNAME\</reservation.username>
    <reservation.password>\YOUR_PASSWORD\</reservation.password>
    <reservation.scheduleView>\SCHEDULE_VIEW\</reservation.scheduleView>
    <reservation.day>\DAY\</reservation.day>
    <reservation.time>\TIME\</reservation.time>
    <reservation.weeksAhead>\WEEKS_AHEAD\</reservation.weeksAhead>
  </systemProperties>
  ```
  ***1.2. Directly in _ReservationTest.java_ file***
  ```{r, engine='sh'}
  String username = "\YOUR_USERNAME\";

  String password = "\YOUR_PASSWORD\";

  String scheduleView = "\SCHEDULE_VIEW\";

  String day = "\DAY\";
	
  String time = "\TIME\";

  String weeksAheadStr = "\WEEKS_AHEAD\";

  ```

**2. Run test**
```{r, engine='sh'}
run cmd
navigate to project folder

mvn -Dreservation.username=\YOUR_USERNAME\ -Dreservation.password=\YOUR_PASSWORD\ -Dreservation.scheduleView=\SCHEDULE_VIEW\ -Dreservation.day=\DAY\ -Dreservation.time=\TIME\ -Dreservation.weeksAhead=\WEEKS_AHEAD\ clean test
or
mvn clean test
or
Run project as JUnit test from your IDE
```

## Project
* Clone git repo
```{r, engine='sh'}
git clone https://github.com/martin-pechacek/flight-fare-finder.git
```
