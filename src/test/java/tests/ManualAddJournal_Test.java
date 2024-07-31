package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import listeners.ExtentReportListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ExcelParser;

import java.io.IOException;
import java.util.List;


@Listeners(ExtentReportListener.class)
public class ManualAddJournal_Test extends BaseTest {


    @BeforeMethod
    public void beforeAllTest() {

        addJournalPage = homepage.navigateToAddJournalpage();
        addJournalPage.verifyAddJournalIconIsClickable();


    }

    @Test(priority = 1)
    public void NavigateToAddJorIcon() {

        ExtentReportListener.getTest().assignCategory("Add Journal");
        ExtentReportListener.getTest().log(Status.INFO, "Click Base icon");
        ExtentReportListener.getTest().log(Status.INFO, "Click add journal icon");

        addJournalPage.verifyAddJournalIconIsClickable();
        ExtentReportListener.getTest().log(Status.INFO, "Navigated successfully to the Add Journals page");


    }

    @Test(priority = 2)
    public void addJournals() throws IOException, InterruptedException {


        ExtentReportListener.getTest().log(Status.INFO, "Adding Journal Data From here");
        List<Object[]> excelData = ExcelParser.getExcelData("D:\\ZoneTest\\AddJournals.xlsx", 0);


        for (Object[] row : excelData) {

            if (row.length == 3) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();

                addJournalPage.generalAddJournals();

            }
            else{
                System.out.println("Row does not have expected numbers: " + row.length);
            }
        }
    }
}
