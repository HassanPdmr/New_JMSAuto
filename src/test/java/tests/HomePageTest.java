package tests;

import base.BaseTest;
import listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentReportListener.class)
public class HomePageTest extends BaseTest {


    @Test(priority = 0)
    public void verifyHomeTitle() {

        ExtentReportListener.getTest().assignCategory("HomePageTests");
        ExtentReportListener.getTest().info("Login successfully");
        ExtentReportListener.getTest().info("verifing the title");
        ExtentReportListener.getTest().info("Title expected as LMS");
        String titlename = homepage.CheckTitle();

        Assert.assertEquals(titlename, "JMS", "title wrong");
        ExtentReportListener.getTest().info(titlename + " " + " right");


    }

    @Test(priority = 1)
    public void verifyUrl() {

        ExtentReportListener.getTest().assignCategory("HomePageTests");
        ExtentReportListener.getTest().info("Login successfully");
        ExtentReportListener.getTest().info("validating the url");
        ExtentReportListener.getTest().info("url expected as http://192.168.1.39:3002/jms/stock");
        String urlname = homepage.CheckUrl();

        Assert.assertEquals(urlname, "http://192.168.1.39:3002/jms/stock", "Wrong url");
        ExtentReportListener.getTest().info(urlname + " " + "  is right");

    }


    @Test(priority = 2)
    public void verifyIconIsDisplayed() {
        ExtentReportListener.getTest().assignCategory("HomePageTests");
        ExtentReportListener.getTest().assignCategory("HomePageTests");
        ExtentReportListener.getTest().info("verify icon is display or not");

        boolean visible = homepage.isBaseIconDisplayed();
        System.out.println(visible);

        Assert.assertTrue(visible, "icon not displayed");
        ExtentReportListener.getTest().info("verify icon is displayed");


    }


}



