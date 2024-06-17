package com.tutorialsninja.demo.browserfactory;

import com.tutorialsninja.demo.propertyreader.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

/**
 * Project-2 tutorialsninja-sw-5
 * baseUrl: http://tutorialsninja.com/demo/index.php?
 * Page Object project with page factory with extent report, Use data provider, create runner.xml file
 * to run test
 * Project name: com-tutorialsninja
 * Group-Id : com.tutorialsninja.demo
 * Requirement:
 * Create the package ‘browserfactory’ and create the class with the name ‘Managebrowser’
 * inside the ‘browserfactory’ package. And write the browser setup code inside the class
 * ‘Managebrowser’.
 * Create the package utilities and create the class with the name ‘Utility’ inside the ‘utilities’
 * package. And write the all the utility methods in it’.
 * Create the package pages and create all pages classes in it.
 * Create the package propertyreader and create the PropertyReader class init.
 * ● Create testbase package and create TestBase class init.
 * ● Create the testsuite package and create followin classes init.
 */

public class ManageBrowser {

    public static WebDriver driver;
    private String baseUrl = PropertyReader.getInstance().getProperty("baseUrl");
    private int implicitlyWait = Integer.parseInt(PropertyReader.getInstance().getProperty("implicitlyWait"));

    // initialization of current page through driver in constructor
    public ManageBrowser(){
        PageFactory.initElements(driver, this);
    }

    public void selectBrowser(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            driver = new InternetExplorerDriver();
        } else {
            System.out.println("Wrong browser name");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
        driver.get(baseUrl);
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
