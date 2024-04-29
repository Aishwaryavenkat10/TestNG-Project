package com.bw.qa.basetest;

import com.bw.qa.factory.DriverFactory;
import com.bw.qa.pages.CreatePostPage;
import com.bw.qa.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Properties prop;
    DriverFactory df;
    protected LoginPage loginPage;
    protected CreatePostPage createPostPage;
    @Parameters({"browser"})
    @BeforeTest
    public void setUp(String browserName){
        df = new DriverFactory();
        prop = df.initProp();

        if (browserName!=null){
            prop.setProperty("browser",browserName);
        }

       driver =  df.init_Driver(prop);
       loginPage = new LoginPage(driver);
    }
    @AfterTest
    public void AfterTest(){
        driver.quit();
    }
}
