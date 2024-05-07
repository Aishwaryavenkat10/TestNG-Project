package com.bw.qa.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class OptionManager {
    private Properties prop;
    private ChromeOptions co;
    private FirefoxOptions fo;
    private EdgeOptions eo;
    public OptionManager(Properties prop){
        this.prop = prop;
    }
    public ChromeOptions getChromeOptions(){
        co = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless").trim())){
            co.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito").trim())){
            co.addArguments("--incognito");
        }
        if (Boolean.parseBoolean(prop.getProperty("remote"))){
            co.setCapability("browserName","chrome");
//            co.setBrowserVersion(prop.getProperty("browserversion").trim());
//
//            Map<String, Object> selenoidOptions = new HashMap<>();
//            selenoidOptions.put("screenResolution", "1280x1024x24");
//            selenoidOptions.put("enableVNC", true);
//            selenoidOptions.put("name", prop.getProperty("testname"));
//            co.setCapability("selenoid:options", selenoidOptions);
        }

        return co;
    }
    public FirefoxOptions getFirefoxOptions(){
        fo = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless").trim())){
            fo.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito").trim())){
            fo.addArguments("--incognito");
        }

        return fo;
    }

    public EdgeOptions getEdgeOptions(){
        eo = new EdgeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless").trim())){
            eo.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito").trim())){
            eo.addArguments("--inprivate");
        }

        return eo;
    }

}
