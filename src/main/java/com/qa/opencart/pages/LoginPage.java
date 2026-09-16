package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    //1. string locators
    private Page page;
    private String emailId="//input[@id='input-email']";
    private String password="//input[@id='input-password']";
    private String loginbtn="input[type='submit']";
    private String forgotPwdLink="//div[@class='form-group']//a[text()='Forgotten Password']";
    private String logOutBtn="//a[@class='list-group-item'][normalize-space()='Logout']";
   //2.page constructor
    public LoginPage(Page page){

        this.page=page;
    }

    //3.page actions
    public String getloginpagetitle(){
        return page.title();

    }
    public Boolean isForgotPwdLinkExist(){
        return page.isVisible(forgotPwdLink);
    }

    public Boolean login(String appUserName,String appPassword){
        System.out.println("App creds: "+appUserName+" "+appPassword);
        page.fill(emailId,appUserName);
        page.fill(password,appPassword);
        page.click(loginbtn);
        if(page.isVisible(logOutBtn)){
            System.out.println("Login Successful....");
            return true;
        }
        return false;
    }
}
