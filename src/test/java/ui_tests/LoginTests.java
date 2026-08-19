package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import static utils.PropertiesReader.*;

import static utils.PropertiesReader.getProperty;

public class LoginTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToRegistrationLoginPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest(){
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "username"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnLogin();
        ContactsPage contactsPage = new ContactsPage(getDriver());
        softAssert.assertTrue(contactsPage.isLinkContactsDisplayed(), "validate isLinkContactsDisplayed");
        softAssert.assertTrue(contactsPage.isUrlContainsText("contacts"), "validate url");
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeWithWrongPasswordTest(){
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "username"))
                .password(getProperty("base.properties", "wrongPswForLogin"))
                .build();
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password"));
    }

    @Test
    public void loginNegativeAllFieldsEmptyWOTypeFormTest(){
        loginPage.clickBtnLogin();
        //Assert.assertTrue(loginPage.closeAlert().contains("Wrong email or password"));
        Assert.assertEquals(loginPage.closeAlert(), "Wrong email or password");
    }


}
