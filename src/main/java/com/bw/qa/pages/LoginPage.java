package com.bw.qa.pages;

import com.bw.qa.constants.AppConstants;
import com.bw.qa.util.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage  {
    private WebDriver driver;
    private ElementUtil elementUtil;

    private By userName = By.xpath("//input[@type='email']");
    private By verificationBtn = By.xpath("//button[@type='submit']");
    private By password = By.xpath("//div[@class='form-group']//input");

    private By loginBtn = By.xpath("//button[@type='submit']");


    public LoginPage(WebDriver driver){

        this.driver = driver;
        elementUtil = new ElementUtil(this.driver);
    }

    @Step("Doing Login with username is :{0} and password {1} ")
    public CreatePostPage login(String un, String pwd) throws InterruptedException {

        elementUtil.waitForVisibilityOfElement(userName, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(un);
        Thread.sleep(10);
//        elementUtil.doClick(verificationBtn);
        elementUtil.waitForVisibilityOfElement(verificationBtn,AppConstants.MEDIUM_DEFAULT_WAIT).click();
        Thread.sleep(10);
//        elementUtil.doSendKeys(password,pwd);
        elementUtil.waitForVisibilityOfElement(password,AppConstants.SHORT_DEFAULT_WAIT).sendKeys(pwd);
        Thread.sleep(10);
//        elementUtil.doClick(loginBtn);
        elementUtil.waitForVisibilityOfElement(loginBtn,AppConstants.SHORT_DEFAULT_WAIT).click();
        Thread.sleep(10);
        return new CreatePostPage(driver);

    }

}
