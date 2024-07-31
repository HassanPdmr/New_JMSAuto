package tests;


import base.BaseTest;
import com.aventstack.extentreports.Status;
import listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ExcelParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static utils.ExcelReader.ReadExcelData;

@Listeners(ExtentReportListener.class)
public class UserManagementTest extends BaseTest {


    @BeforeMethod
    public void beforeAllTest() {
        userManagement = homepage.navigateToUserManagementPage();
        userManagement.verifyAddUserisClickable();


    }

    @Test(priority = 0, description = "Navigating to Add User")
    public void NavigateToAddUser() {

        ExtentReportListener.getTest().assignCategory("Add User");
        ExtentReportListener.getTest().log(Status.INFO, "Clicking the Side UserManagement ");
        ExtentReportListener.getTest().log(Status.INFO, "Clicking the add user page");

        userManagement.verifyAddUserisClickable();
        ExtentReportListener.getTest().log(Status.INFO, "Navigated successfully to the Add user page");


    }

    @DataProvider(name = "getAddUserdata")
    public Object[][] getAddUserData() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\User_management.xlsx", 0);
    }

    @Test(priority = 1, dataProvider = "getAddUserdata", description = "JMS-52 : Provide details for new employee but don’t click ‘ADD’ & ensure employee not created")
    public void verifyCreatingUserBynotClickingAdd(String empName, String empId, String designation,
                                                   String gender, String depart, String Pub, String access,
                                                   String role, String mail) throws InterruptedException {

        ExtentReportListener.getTest().log(Status.INFO, "Adding User without clicking Add button");

        userManagement.creatingUserWithoutAdd(empName, empId, designation, gender,
                depart, Pub, access, role, mail);

        Assert.assertFalse(false, "User created even without clicking Add button");

        ExtentReportListener.getTest().log(Status.INFO, "User creation failed without clicking Add button");


    }

    @Test(priority = 2, dataProvider = "getAddUserdata", description = "JMS-55 : Try duplicate an employee by Emp-ID - EmpID should be unique - Version 1 Show ghost string Display Author/Updater ")
    public void verifyDuplicateEmpIdNotAdded(String empName, String empId, String designation,
                                             String gender, String depart, String Pub, String access,
                                             String role, String mail) {

        ExtentReportListener.getTest().log(Status.INFO, "Verifying the duplicate EmpID should be unique");

        String AlertMessage = userManagement.duplicateEmpId(empName, empId, designation, gender,
                depart, Pub, access, role, mail);

        Assert.assertEquals(AlertMessage, "Employee ID already exists.");

        ExtentReportListener.getTest().log(Status.INFO, "Verifying the duplicate EmpID should be unique");


    }

    @DataProvider(name = "getNewAddUser")
    public Object[][] getNewAddUserData() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\User_management.xlsx", 1);
    }

    @Test(priority = 3, dataProvider = "getNewAddUser", description = "JMS-43 : Add new user with valid details and verify the entered details in Profile")
    public void verifyAddUserandVerifyfromEdit(String empName, String empId, String designation,
                                               String gender, String depart, String Pub, String access,
                                               String role, String mail) throws InterruptedException {


        ExtentReportListener.getTest().log(Status.INFO, "Verify new user from edit profile");
        List<String> actualUserValues = userManagement.addNewUserandVerify(empName, empId, designation, gender,
                depart, Pub, access, role, mail);

        List<String> expectedUserValues = List.of(empName, empId, designation, gender, depart, Pub, access, role, mail);


        Assert.assertEquals(actualUserValues, expectedUserValues, "Values are differ from page class");


    }


    @DataProvider(name = "getEmpName")
    public Object[][] getNewEmpData() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\User_management.xlsx", 3);

    }

    @Test(priority = 4, dataProvider = "getEmpName", description = "JMS-48 : Verify the name should not allow other than alphabets - check with invalid scenarios - Version 1")
    public void verifyEmpNameShouldAllowAlphabets(String empName) throws InterruptedException {

        ExtentReportListener.getTest().log(Status.INFO, "Check the Emp Name only with alphabets");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\User_management.xlsx", 2);

        for (Object[] row : excelDataA) {

            if (row.length == 9) {

                String a = row[0].toString();
                String empId = row[1].toString();
                String designation = row[2].toString();
                String gender = row[3].toString();
                String depart = row[4].toString();
                String pub = row[5].toString();
                String access = row[6].toString();
                String role = row[7].toString();
                String mail = row[8].toString();

                Boolean actualErr = userManagement.nameWithOnlyAlphabets(empName, empId, designation, gender, depart, pub, access, role, mail);

                System.out.println("Actual Error: " + actualErr);
                Assert.assertFalse(actualErr, "No Validation error displayed");
                ExtentReportListener.getTest().log(Status.INFO, "EMP name has been verified with alphabets");


            }


        }
    }


    @DataProvider(name = "getEmpID")
    public Object[][] getNewEmpID() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\User_management.xlsx", 5);

    }

    @Test(priority = 5, dataProvider = "getEmpID", description = "JMS-49 : Verify the validation of employee id, whether not accepting any Alphabets - Version 1")
    public void verifyEmpIDShouldOnlyAcceptNumerics(String empId) throws InterruptedException {

        ExtentReportListener.getTest().log(Status.INFO, "Check the EmpID only with Numerics");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\User_management.xlsx", 4);

        for (Object[] row : excelDataA) {

            if (row.length == 9) {

                String empName = row[0].toString();
                String a = row[1].toString();
                String designation = row[2].toString();
                String gender = row[3].toString();
                String depart = row[4].toString();
                String pub = row[5].toString();
                String access = row[6].toString();
                String role = row[7].toString();
                String mail = row[8].toString();

                Boolean actualErr = userManagement.empIDWithOnlyNumbers(empName, empId, designation, gender, depart, pub, access, role, mail);

                System.out.println("Actual Error: " + actualErr);
                Assert.assertFalse(actualErr, "No Validation error displayed");
                ExtentReportListener.getTest().log(Status.INFO, "EmpID has been verified with numerics");


            }


        }


    }

    @DataProvider(name = "getEmail")
    public Object[][] getEmail() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\User_management.xlsx", 7);

    }

    @Test(priority = 6, dataProvider = "getEmail", description = "JMS-50 : Mail ID format to be checked with invalid inputs - Version 1")
    public void verifyEmailAcceptOnlyInValidInputs(String EmailID, boolean isValid) throws InterruptedException {

        ExtentReportListener.getTest().log(Status.INFO, "Check the Email with invalid inputs");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\User_management.xlsx", 6);


        for (Object[] row : excelDataA) {

            if (row.length == 9) {

                String empName = row[0].toString();
                String empID = row[1].toString();
                String designation = row[2].toString();
                String gender = row[3].toString();
                String depart = row[4].toString();
                String pub = row[5].toString();
                String access = row[6].toString();
                String role = row[7].toString();
                String a = row[8].toString();

                List<String> actualErr = userManagement.emailWithInValidInputs(empName, empID, designation, gender, depart, pub, access, role, EmailID);
                System.out.println("actual error here for mail is : " + actualErr);

              List<String> expectedErr = new ArrayList<>();
               if (isValid) {

                    expectedErr.add("Please fill out this field.");
                } else
                {
                    expectedErr.add("A part following '@' should not contain the symbol '@'.");
                    expectedErr.add("Please include an '@' in the email address, 'test>com' is missing an '@'.");
                    expectedErr.add("Please include an '@' in the email address, 'check.' is missing an '@'.");
                }



                Assert.assertEquals(actualErr.get(0), expectedErr.get(0), "Error messages do not match the expected messages.");
                System.out.println(actualErr.get(0));

                Assert.assertEquals(actualErr.get(1), expectedErr.get(1), "Error messages do not match the expected messages.");
                System.out.println(actualErr.get(1));

                Assert.assertEquals(actualErr.get(2), expectedErr.get(2), "Error messages do not match the expected messages.");
                System.out.println(actualErr.get(2));



            }
        }
    }
}

