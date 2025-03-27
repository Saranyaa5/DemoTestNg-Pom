package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

   public HomePage(WebDriver driver) {
       super(driver);
      
   }

   @FindBy(xpath="//p[contains(text(),'Welcome to the new Tricentis store!')]")
   private WebElement homePageMessage;
   
  @FindBy(linkText="Register")
  private WebElement registerButton;

   public String getHomePageMessage() {
       return homePageMessage.getText();
   }
   public void clickRegister() {
       registerButton.click();
   }
}
