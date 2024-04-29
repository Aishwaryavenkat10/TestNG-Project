package com.bw.qa.pages;

import com.bw.qa.constants.AppConstants;
import com.bw.qa.util.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreatePostPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    private By createPostBtn = By.xpath("//button[@id='create-button']");

    private By dropdownBtn =By.xpath("//span[@class='Select-arrow']");
    private By selectBtn =By.xpath("//div/button[text()='Select']");
    private By textArea =By.xpath("//div[@class='form-group']/textarea");
    private By publishPostBtn =By.xpath("//button[text()='Publish post']");
    private By logo = By.xpath("//img[@class='hyphenMonster']");


    public CreatePostPage(WebDriver driver){

        this.driver = driver;
        elementUtil = new ElementUtil(this.driver);
    }

    public Boolean checkLogoExist(){
        return elementUtil.waitForVisibilityOfElement(logo, AppConstants.POLLING_DEFAULT_WAIT).isDisplayed();
    }
    @Step("Clicking on create post button")
    public void clickOnCreateNewPostBtn(){

          elementUtil.waitForVisibilityOfElement(createPostBtn,AppConstants.SHORT_DEFAULT_WAIT).click();
    }
    @Step("Selecting the post option")
    public void selectPostOption(String option){

        if (option.toLowerCase() == "anonymous"){
            option = "Anonymous";
        }
        else if (option.toLowerCase() == "official"){
            option = "Official";
        }
        else if (option.toLowerCase() == "named"){
            option = "Named";
        }
        By optionElement  =By.xpath("//span[text()='"+option+"']/parent::label//input");
        elementUtil.waitForVisibilityOfElement(optionElement,AppConstants.SHORT_DEFAULT_WAIT).click();
    }
    @Step("Selecting the post type")
    public void selectPostType(String type){
        if(type.toLowerCase() == "open"){
            type = "OPEN";
        }
        else if (type.toLowerCase() == "multiple choice"){
            type = "MULTIPLE CHOICE";
        }

        By typeElement = By.xpath("//div/button[text()='"+type+"']");
        elementUtil.waitForVisibilityOfElement(typeElement,AppConstants.SHORT_DEFAULT_WAIT).click();
    }
    @Step("Selecting the group")
    public void selectGroup(String group){

        elementUtil.doClick(dropdownBtn);
        By groupType = By.xpath("//div[@class='Select-menu-outer']/div/div/div[text()='"+group+"']");
        elementUtil.doClick(groupType);
        elementUtil.doClick(selectBtn);

    }
    @Step("creating the post")
    public void createPost(String content){
        elementUtil.waitForVisibilityOfElement(textArea,AppConstants.SHORT_DEFAULT_WAIT).sendKeys(content);
        elementUtil.doClick(publishPostBtn);

    }

}
// //span[text()='Anonymous']/parent::label//input
//setTimeout(()=>{debugger;},5000);