package com.qa.opencart.base;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import java.util.Properties;

public class BaseTest {
    PlaywrightFactory pf;
    Page page;
    protected Properties prop;
    protected HomePage homePage;
    protected LoginPage loginPage;
    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String browserName) {
        pf = new PlaywrightFactory();
        prop = pf.init_prop();
        System.out.println("prop = " + prop);
        if(browserName!=null){
            prop.setProperty("browser", browserName);
        }

        page = pf.initBrowser(prop);
        System.out.println("page = " + page);

        homePage = new HomePage(page);
        System.out.println("homePage = " + homePage);

    }

    @AfterClass
    public void tearDown(){

        page.context().browser().close();
    }
}
