package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private Page page;
    //1. String Locators
    private String search="input[name='search']";
    private String searchIcon="div#search button";
    private String searchHeader="div#content h1";
    private String accClick="a[title='My Account']";
    private String loginLink="//a[text()='Login']";


    //2.page constructor
    public HomePage(Page page){
        this.page=page;
    }

    //3. page actions
    public String getHomeTitle(){
        String title= page.title();
        System.out.println("page title is:"+title);
        return title;
    }

    public String HomePageUrl(){
        String url= page.url();
        System.out.println("page url is:"+url);
        return url;
    }

    public String doSearch(String Product){
        page.fill(search, Product);
        page.click(searchIcon);
        String header=page.locator(searchHeader).textContent();
        System.out.println("header is:"+header);
        return header;
    }

    public LoginPage navigateToLoginPage(){
        page.click(accClick);
        page.click(loginLink);
        return new LoginPage(page);
    }
}
