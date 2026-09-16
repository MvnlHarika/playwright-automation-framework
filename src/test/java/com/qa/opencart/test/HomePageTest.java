package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.ApplicationConstants;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {


    @Test
    public void homePageTitleTest(){
        String actualTitle= homePage.getHomeTitle();
        Assert.assertEquals(actualTitle, ApplicationConstants.PAGE_TITLE);
    }
    @Test
    public void homePageUrlTest(){
        String actualUrl= homePage.HomePageUrl();
        Assert.assertEquals(actualUrl,prop.getProperty("url").trim());
    }
    @DataProvider
    public Object[][] getProductData(){
        return new Object[][]{
                {"Macbook"},
                {"iMac"},
                {"Samsung"}
        };
    }
    @Test(dataProvider = "getProductData")
    public void searchTest(String productName){
        String actualHeader=homePage.doSearch(productName);
        Assert.assertEquals(actualHeader,"Search - " + productName);
    }

}
