package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WDListener;

import java.lang.reflect.Method;

public class AppManager {
    private WebDriver driver;
    public WebDriver getDriver(){
        return driver;
    }

    public Logger logger = LoggerFactory.getLogger(AppManager.class);

    @BeforeMethod
    public void setup(Method method){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("start testing with method " + method.getName());
        WebDriverListener webDriverListener = new WDListener();
        driver = new EventFiringDecorator<>(webDriverListener).decorate(driver);
    }

    @AfterMethod(enabled = false)
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
