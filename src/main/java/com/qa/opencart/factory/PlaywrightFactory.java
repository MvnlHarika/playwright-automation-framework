package com.qa.opencart.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class PlaywrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties prop;
    private static ThreadLocal<Browser> tlBrowser=new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlBrowserContext=new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage=new ThreadLocal<>();
    private static ThreadLocal<Playwright> tlPlaywright=new ThreadLocal<>();

    public static Playwright getPlaywright(){
        return tlPlaywright.get();
    }
    public static Browser getBrowser(){
        return tlBrowser.get();
    }
    public static BrowserContext getBrowserContext(){
        return tlBrowserContext.get();
    }
    public static Page getPage(){
        return tlPage.get();
    }

    public Page initBrowser(Properties prop){
        String browserName=prop.getProperty("browser").trim();
        System.out.println("Browser name is: "+browserName);
        //playwright = Playwright.create();
        tlPlaywright.set(Playwright.create());
        switch(browserName.toLowerCase()){
            case "chromium":
                //browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)));
                break;
            case "firefox":
                //browser=playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
                tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)));
                break;
            case "safari":
                //browser=playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
                tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)));
                break;
            case "chrome":
                //browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false).setSlowMo(500));
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false).setSlowMo(1000)));
                break;
            case "default":
                System.out.println("Please pass the right browser name...."+browserName);
                break;
        }
        //browserContext=browser.newContext();
        tlBrowserContext.set(getBrowser().newContext());
        //page=browserContext.newPage();
        tlPage.set(getBrowserContext().newPage());
        //page.navigate(prop.getProperty("url").trim());
        getPage().navigate(prop.getProperty("url").trim());
        return getPage();
    }
    //this meathod is used to initialize properties from config file
    public Properties init_prop(){
       try{ FileInputStream ip=new FileInputStream("./src/test/resources/config/config.properties");
           prop=new Properties();
           prop.load(ip);
       }catch(FileNotFoundException e){
           e.printStackTrace();
       } catch (IOException e) {
          e.printStackTrace();
       }
       return prop;
    }
    public static String takeScreenshot(){
        String path=System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
//        getPage().screenshot(new Page.ScreenshotOptions()
//                .setPath(Paths.get(path))
//                .setFullPage(true));
        byte[] buffer=getPage().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(path))
                .setFullPage(true));
        String base64path=Base64.getEncoder().encodeToString(buffer);
        return base64path;
    }
}
