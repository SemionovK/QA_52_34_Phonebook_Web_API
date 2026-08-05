package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.Random;

public class RegistrationTests extends AppManager {
    @BeforeMethod
    public void goToRegistrationLoginPage(){
        new HomePage(getDriver()).clickBtnLogin();
    }

    @Test
    public void registrationPositiveTest(){
        int i = new Random().nextInt(1000);
        UserLombok user = UserLombok.builder()
                .username("bruno"+i+"@gmail.com")
                .password("QAZ123!lnk")
                .build();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();
    }
}
