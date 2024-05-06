package com.bw.qa.factory;

import com.bw.qa.exception.FrameworkException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class DriverFactory {
    WebDriver driver;
    Properties prop;
    OptionManager optionManager;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
    public WebDriver init_Driver(Properties prop){
        String browserName = prop.getProperty("browser");
//        String browserName = System.getProperty("browser");
        System.out.println("The given BrowserName is: "+browserName);
        optionManager = new OptionManager(prop);
        switch (browserName.toLowerCase().trim()){
            case "chrome":
                if (Boolean.parseBoolean(prop.getProperty("remote"))){
                    //run it on grid
                    initRemoteDriver(browserName);
                }else {
                    //run it on local
                    tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
                }
                break;
            case "firefox":
                if (Boolean.parseBoolean(prop.getProperty("remote"))){
                    initRemoteDriver(browserName);
                }else {

                    tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
                }
                break;
            case "edge":
                if (Boolean.parseBoolean(prop.getProperty("remote"))){
                    initRemoteDriver(browserName);
                }else {
                    tlDriver.set(new EdgeDriver(optionManager.getEdgeOptions()));
                }

                break;
            case "safari":
//                driver = new SafariDriver();
                tlDriver.set(new SafariDriver());
                break;
            default:
                System.out.println("Please Provide Right browser name : "+browserName);
                throw new FrameworkException("No Browser found...");
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("url"));
        return getDriver();
    }

    public static WebDriver getDriver(){
        return tlDriver.get();
    }
    public Properties initProp(){
        FileInputStream ip = null;
        prop = new Properties();

        String envName = System.getProperty("env");
        System.out.println("Env name is: "+envName);
        try{
        if (envName == null){
            ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
        }
        else {
            switch (envName.toLowerCase().trim()) {
                case "qa":
                    ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
                    break;
                case "dev":
                    ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
                    break;
                case "stage":
                    ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
                    break;
                case "uat":
                    ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
                    break;
                case "prod":
                    ip = new FileInputStream("./src/test/resources/config/config.properties");
                    break;
                default:
                    System.out.println("Please provide right environment name" + envName);
                    throw new FrameworkException("Wrong environment name: " + envName);
                }
            }
        }
        catch (FileNotFoundException e){

        }
        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * run tests on grid
     * @param browserName
     */
    private void initRemoteDriver(String browserName){
        System.out.println("Running the tests on GRID with browser"+browserName);
        try {
            switch (browserName.toLowerCase().trim()) {
                case "chrome":
                    tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionManager.getChromeOptions()));
                    break;
                case "firefox":
                    tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionManager.getFirefoxOptions()));
                    break;
                case "edge":
                    tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionManager.getEdgeOptions()));
                    break;
                default:
                    System.out.println("Wrong browser info");
                    break;

            }
        }catch (MalformedURLException e){

        }
    }
    /**
     * take screenshot
     */
    public static String getScreenshot(String methodName) {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileHandler.copy(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
