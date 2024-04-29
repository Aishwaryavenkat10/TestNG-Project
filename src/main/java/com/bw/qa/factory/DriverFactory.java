package com.bw.qa.factory;

import com.bw.qa.exception.FrameworkException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
//                driver = new ChromeDriver(optionManager.getChromeOptions());
                tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
                break;
            case "firefox":
//                driver = new FirefoxDriver(optionManager.getFirefoxOptions());
                tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
                break;
            case "edge":
//                driver = new EdgeDriver(optionManager.getEdgeOptions());
                tlDriver.set(new EdgeDriver(optionManager.getEdgeOptions()));
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
            ip = new FileInputStream("./src/test/resources/config/config.properties");
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
