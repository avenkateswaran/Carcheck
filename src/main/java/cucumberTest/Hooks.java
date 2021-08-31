package cucumberTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Hooks {

    public static WebDriver driver;
    public static String urlval = null;
    public static String browsertype=null;


    String workingDir = System.getProperty("user.dir");


    @Before
    public void InitializeSuite() {
        driver = null;

        //get the environment values
        System.out.println(workingDir);
        File filenew = new File(workingDir+"\\src\\main\\resources\\Config.properties");

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(filenew);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        //load properties file
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        urlval = prop.getProperty("url");
        System.out.println(urlval);
        browsertype=prop.getProperty("BrowserType");
        System.out.println(browsertype);

        //create the driver
        try {
            if(browsertype.contentEquals("Chrome")) {
                ChromeOptions o = new ChromeOptions();
                o.addArguments("disable-extensions");
                o.addArguments("--start-maximized");
                System.setProperty("webdriver.chrome.driver", workingDir+"\\src\\main\\resources\\chromedriver.exe");
                driver=new ChromeDriver(o);
                driver.get("https://cartaxcheck.co.uk/");
            }
        } catch (Exception e) {
            e.getMessage();

        }

    }

    @After
    public void quitDriver() {
//        if (driver != null) {
//            driver.quit();
//        }
    }


}
