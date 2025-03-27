package com.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasePage {
    
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }
    
    @FindBy(tagName = "h1")
    private WebElement registrationTitle;

    @FindBy(id = "gender-male")
    private WebElement genderMale;

    @FindBy(id = "gender-female")
    private WebElement genderFemale;

    @FindBy(id = "FirstName")
    private WebElement firstName;

    @FindBy(id = "LastName")
    private WebElement lastName;

    @FindBy(id = "Email")
    private WebElement email;

    @FindBy(id = "Password")
    private WebElement password;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPassword;

    @FindBy(id = "register-button")
    private WebElement registerButton;
    
    public String getRegistrationTitle() {
        return registrationTitle.getText();
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            genderMale.click();
        } else if (gender.equalsIgnoreCase("female")) {
            genderFemale.click();
        }
    }
    public void enterFirstName(String fname) {
        firstName.sendKeys(fname);
    }

    public void enterLastName(String lname) {
        lastName.sendKeys(lname);
    }

    public void enterEmail(String mail) {
        email.sendKeys(mail);
    }

    public void enterPassword(String pass) {
        password.sendKeys(pass);
    }

    public void enterConfirmPassword(String confirmPass) {
        confirmPassword.sendKeys(confirmPass);
    }

    public void clickRegisterForm() {
        registerButton.click();
    }
}



  

