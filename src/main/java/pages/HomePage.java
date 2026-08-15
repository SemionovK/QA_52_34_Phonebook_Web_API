package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static utils.PropertiesReader.getProperty;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        setDriver(driver);
       driver.get(getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//a[text()='LOGIN']")
    WebElement btnLogin;
    @FindBy(xpath = "//form/input[1]")
    WebElement inputEmail;


    public void clickBtnLogin(){
        btnLogin.click();
    }

    public void method(){
        WebElement login = driver.findElement(By
                .xpath("//a[text()='LOGIN']"));
        //login.click();
        WebElement inputEmail = driver.findElement(By
                .xpath("//form/input[1]"));
        login.click();
        inputEmail.sendKeys("dfrgtey@drety.vbg");
    }

    public void ajaxMethod(){
        btnLogin.click();
        inputEmail.sendKeys("fgthyr@cvfgt.bnm");
    }
}


