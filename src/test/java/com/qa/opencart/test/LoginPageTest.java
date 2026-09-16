package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.ApplicationConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(priority = 1)
    public void loginPageNavigationTest(){
        loginPage=homePage.navigateToLoginPage();
        String actualLoginPageTitle=loginPage.getloginpagetitle();
        System.out.println("Page actual title is: "+actualLoginPageTitle);
        Assert.assertEquals(actualLoginPageTitle, ApplicationConstants.LOGIN_PAGE_TITLE);
    }
    @Test(priority = 2)
    public void forgotPwdLinkTest(){
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());

    }
    @Test(priority = 3)
    public void appLoginTest(){
        Assert.assertTrue(loginPage.login(prop.getProperty("username").trim(), prop.getProperty("password").trim()));
    }
}
