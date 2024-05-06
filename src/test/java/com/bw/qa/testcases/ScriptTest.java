package com.bw.qa.testcases;

import com.bw.qa.basetest.BaseTest;
import com.bw.qa.listeners.TestAllureListener;
import io.qameta.allure.*;
import net.bytebuddy.build.Plugin;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Epic("Epic 203: Design Login page and create feature post")
@Story("Login page features and post page feature ")
@Feature("F52: Feature login page")
@Listeners(TestAllureListener.class)
public class ScriptTest extends BaseTest {
    @Description("verifying user is able to login with correct credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1)
    public void loginTest() throws InterruptedException {
        createPostPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertTrue(createPostPage.checkLogoExist());
    }

    @Severity(SeverityLevel.BLOCKER)
    @Description("Creating post feature test...")
    @Test(priority = 2)
    public void createPostTest() throws InterruptedException {
        createPostPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        createPostPage.clickOnCreateNewPostBtn();
        createPostPage.selectPostOption("official");
        createPostPage.selectPostType("open");
        createPostPage.selectGroup("Arnaud only");
        createPostPage.createPost("Never to old to learn");
    }






}
