package com.test;
import com.pages.*;
import com.test.*;
import com.util.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.pages.HomePage;

@Listeners(ListenerClass.class)
public class HomeTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void initializePage() {
        homePage = new HomePage(getDriver());
    }

    @Test
    public void verifyHomePageMessage() {
        System.out.println("run1");
        String expected_Homepage_Title = "Welcome to the new Tricentis store!";
        String actual_Homepage_Title = homePage.getHomePageMessage();
        Assert.assertEquals(actual_Homepage_Title, expected_Homepage_Title, "Home page message does not match");
    }

    @Test(priority = 1, dependsOnMethods = "verifyHomePageMessage")
    public void clickRegisterButton() {
        System.out.println("run2");
        homePage.clickRegister();
    }
}
