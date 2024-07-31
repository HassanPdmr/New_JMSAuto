package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import listeners.ExtentReportListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelParser;

import java.io.IOException;
import java.util.List;

import static utils.ExcelReader.ReadExcelData;

public class PreRequestTest extends BaseTest {


    @Test
    public void NavigatetoBaseIcon() {

        prerequestpage = homepage.navigateToCommonpage();
        prerequestpage.navigatetobaseicon();
    }

//
//    @DataProvider(name = "getPubData")
//    public Object[][] getPublisherData() throws IOException {
//
//        return ReadExcelData("D:\\ZoneTest\\PreRequest.xlsx", 0);
//    }

//
//    @Test(priority = 1, dataProvider = "getPubData")
//    public void AddPublisher(String pub_acro, String pub_name) throws InterruptedException {
//
//        prerequestpage.DoAddPub(pub_acro, pub_name);
//        System.out.println("Pubacro: " + pub_acro);
//        System.out.println("PubName: " + pub_name);
//
//
//    }
//
//    @Test(priority = 2)
//    public void AddJournal() throws InterruptedException {
//
//        List<Object[]> excelData = ExcelParser.getExcelData("D:\\ZoneTest\\PreRequest.xlsx", 1);
//        for (Object[] row : excelData) {
//            if (row.length == 3) {
//                String Pub = row[0].toString();
//                String J_acrm = row[1].toString();
//                String J_name = row[2].toString();
//                prerequestpage.DoAddJournal(Pub, J_acrm, J_name);
//                ExtentReportListener.getTest().log(Status.INFO, "Journal Added");
//
//
//            } else {
//                System.out.println("Row does not have expected numbers: " + row.length);
//            }
//
//        }
//
//
//
//
//
//    }
//

//    @DataProvider(name = "AddingArticle")
//    public Object[][] getaddArticledata() throws IOException {
//
//        return ReadExcelData("D:\\ZoneTest\\PreRequest.xlsx", 2);
//    }
//
//
//    @Test(priority =3,dataProvider = "AddingArticle")
//    public void AddArticle(String journalacro, String articleid, String artname, String doinum, String workflow, String username, String password, String jacrm, String pubname) throws InterruptedException {
//
//        prerequestpage.DoAddArticle(journalacro,articleid,artname,doinum,workflow);
//        ExtentReportListener.getTest().log(Status.INFO, "Article Added");
//
//    }

    @DataProvider(name = "AddingUser")
    public Object[][] getaddUser() throws IOException{

        return ReadExcelData("D:\\ZoneTest\\PreRequest.xlsx", 3);

    }

    @Test(priority =4,dataProvider = "AddingUser")
    public void AddUser (String empName, String empId, String designation,
                         String gender, String depart, String Pub, String access,
                         String role, String mail){


        prerequestpage.AddUser(empName,empId,designation,gender,
                depart,Pub,access,role, mail);
        ExtentReportListener.getTest().log(Status.INFO, "User Added");

    }





    @AfterMethod
    public void AfterAllTest() throws InterruptedException {
        prerequestpage.ReloadDashBoard();
    }
}











