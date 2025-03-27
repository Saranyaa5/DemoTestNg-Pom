package com.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import com.pages.HomePage;
import com.pages.RegistrationPage;
import com.util.DataProviderClass;

public class RegistrationTest extends BaseTest {

    private RegistrationPage registrationPage;
    private HomePage homePage;

    @BeforeMethod
    public void initializePage() {
        homePage = new HomePage(getDriver());
        registrationPage = new RegistrationPage(getDriver());

        homePage.clickRegister();

        
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("register"));
    }

    @Test
    public void verifyRegistrationPage() {
        System.out.println("run3");
        String expectedTitle = "Register";
        String actualTitle = registrationPage.getRegistrationTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Registration page title does not match");
    }

    @Test(dataProvider = "excelData", dataProviderClass = DataProviderClass.class, priority = 1, dependsOnMethods = "verifyRegistrationPage")
    public void testRegistrationForm(String gender, String fName, String lName, String email, String password, String confirmPassword, String test_name) {
        
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        if (test_name.equals("register_successfull")) {
            String uniqueEmail = email.split("@")[0] + System.currentTimeMillis() + "@" + email.split("@")[1];
            email = uniqueEmail;
        }

        registrationPage.selectGender(gender);
        registrationPage.enterFirstName(fName);
        registrationPage.enterLastName(lName);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(confirmPassword);
        registrationPage.clickRegisterForm();

        System.out.println("Form submitted for: " + fName + " " + lName);
        
        if (test_name.equals("register_successfull")) {
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='result']")
            ));
            String actualMessage = successMessage.getText();
            String expectedMessage = "Your registration completed";
            Assert.assertEquals(actualMessage, expectedMessage, "Registration success message mismatch!");
        
        } else if (test_name.equals("email_repeated")) {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='validation-summary-errors']/ul/li")
            ));
            String actualMessage = errorMessage.getText();
            String expectedMessage = "The specified email already exists";
            Assert.assertEquals(actualMessage, expectedMessage, "Error message mismatch!");
        
        } else if (test_name.equals("email_empty")) {
            WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@data-valmsg-for='Email']//span")
            ));
            String actualErrorMessage = emailError.getText();
            String expectedErrorMessage = "Email is required.";
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch!");
        }
    }
}
