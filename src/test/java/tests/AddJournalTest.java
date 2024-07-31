package tests;


import base.BaseTest;
import com.aventstack.extentreports.Status;
import listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.ExcelParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import static utils.ExcelReader.ReadExcelData;

@Listeners(ExtentReportListener.class)
public class AddJournalTest extends BaseTest {

    @BeforeMethod
    public void beforeAllTest() {

        addJournalPage = homepage.navigateToAddJournalpage();
        addJournalPage.verifyAddJournalIconIsClickable();


    }

    @Test(priority = 0, description = "Navigating to Add Journals")
    public void NavigateToAddJorIcon() {

        ExtentReportListener.getTest().assignCategory("Add Journal");
        ExtentReportListener.getTest().log(Status.INFO, "Clicking the Base icon");
        ExtentReportListener.getTest().log(Status.INFO, "Clicking the add journal icon");

        addJournalPage.verifyAddJournalIconIsClickable();
        ExtentReportListener.getTest().log(Status.INFO, "Navigated successfully to the Add Journals page");


    }

    @DataProvider(name = "getJrData")
    public Object[][] getJournalData() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 1);
    }

    @Test(priority = 1, dataProvider = "getJrData", description = "Verify Acronym should not be allowed with special characters")
    public void verifyAcrWithSplChar(String J_acrm, String J_name) throws InterruptedException {


        ExtentReportListener.getTest().log(Status.INFO, "Enter Journal Acronym with special char");

        List<Object[]> excelData = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 0);


        for (Object[] row : excelData) {

            System.out.println("Row length: " + row.length);
            System.out.println("Row contents: " + Arrays.toString(row));

            if (!excelData.isEmpty() && excelData.get(0).length > 0) {
                String Pub = excelData.get(0)[0].toString();

                String JrAck = addJournalPage.sameAcrWithSlChar(Pub, J_acrm, J_name);

                System.out.println("Acknowledgement received as: " + JrAck);

                SoftAssert SoftAst = new SoftAssert();
                SoftAst.assertEquals(JrAck, "Special characters are not allowed in the journal name", "Journal name should not allow Special Characters");
            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }

            String J_AcroName = addJournalPage.fromManageJournalsCheck(J_acrm);

            Assert.assertEquals(J_AcroName, J_acrm, "Journal Acroname not added with Special charcters");
            ExtentReportListener.getTest().log(Status.INFO, "Journal has been added even with Special characters in Journal Acronym");


        }


    }

    @Test(priority = 3, description = "Same journal can be created with different publisher")
    public void verifySameJrWithDiffPub() throws InterruptedException {
        ExtentReportListener.getTest().log(Status.INFO, "Enter Same journal with different publisher");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 2);

        for (Object[] row : excelDataA) {

            if (row.length == 6) {

                String PubAcro1 = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubAcro2 = row[3].toString();
                String PubName = row[4].toString();
                String PubNameII = row[5].toString();

                addJournalPage.diffPubSameJournals(PubAcro1, J_acrm, J_name, PubAcro2, PubName, PubNameII);
                Assert.assertTrue(true, "Journal not added with different publisher");
                ExtentReportListener.getTest().log(Status.INFO, "Journal has been added with Different publisher");
            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }
        }

    }

    @Test(priority = 4, description = "Verify Publisher,Title, Acronym can’t be changed after journal is created")
    public void verifyPubNameAcro() {

        ExtentReportListener.getTest().log(Status.INFO, "Enter Same journal with different publisher");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 3);

        for (Object[] row : excelDataA) {

            if (row.length == 4) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();

                List<String> PubDetails = addJournalPage.samePubAcro(Pub, J_acrm, J_name, PubName);

                Assert.assertTrue(PubDetails.contains("Publisher Title: " + PubName));
                Assert.assertTrue(PubDetails.contains("Publisher Acro: " + Pub));
                ExtentReportListener.getTest().log(Status.INFO, "Publisher details were not changed even after journal created");


            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }


        }

    }

    @Test(priority = 5, description = "Ensure the working of recto option at issue stage")
    public void verifyRectoBox() {

        ExtentReportListener.getTest().log(Status.INFO, "Check the recto checkbox whether it is clickable");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 4);

        for (Object[] row : excelDataA) {

            if (row.length == 4) {

                String PubAck = row[0].toString();
                String PubName = row[1].toString();
                String JAcrm = row[2].toString();
                String J_Name = row[3].toString();

                List<Boolean> RectoVisible = addJournalPage.CheckRecto(PubAck, PubName, JAcrm, J_Name);
                String isenable = String.valueOf(RectoVisible.get(0));
                System.out.println(isenable);
                String ischecked = String.valueOf(RectoVisible.get(1));
                System.out.println(ischecked);

                Assert.assertEquals(isenable, "true", "11");
                Assert.assertEquals(ischecked, "true", "222");


            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }
        }

    }

    @Test(priority = 6, description = "Check Copyediting levels(L1,L2,L3) are available only when CopyEditing option is selected ")
    public void verifyCELevels() {

        ExtentReportListener.getTest().log(Status.INFO, "Checking CE level options");
        List<String> text = addJournalPage.CopyEditingLevel();
        String l1 = text.get(0);
        String l2 = text.get(1);
        String l3 = text.get(2);

        SoftAssert SoftAst = new SoftAssert();

        SoftAst.assertEquals(l1, "L1", "Wrong option displayed");
        SoftAst.assertEquals(l2, "L2", "Wrong option displayed");
        SoftAst.assertEquals(l3, "L3", "Wrong option displayed");


    }

    @Test(priority = 7, description = "Ensure Trim sizes can be given only numerical values")
    public void verifyTrimSize() throws InterruptedException {

        ExtentReportListener.getTest().log(Status.INFO, "Trim size field box has been examined");

        boolean isNumericType = addJournalPage.TrimSizeIsNumeric();
        Assert.assertTrue(isNumericType, "Trim size should be numeric");

        ExtentReportListener.getTest().log(Status.INFO, "Trim size for Width and Height test completed");


    }

    @Test(priority = 8, description = "Give TATdate for General and ensure the same schedule can be copied to FastTrack")
    public void verifyGeneralToFastCopyTat() {


        List<String> actualValues = addJournalPage.GenToFastCopyTat();

        List<String> expectedValues = List.of("1", "2", "3", "4", "5", "6", "7", "8",
                "9", "1",
                "2", "3", "4",
                "5", "6",
                "7", "8", "9");

        Assert.assertEquals(actualValues, expectedValues, "Values retrieved from page class do not match expected values");

        ExtentReportListener.getTest().log(Status.INFO, "Values are imported from General to Fast track");


    }

    @Test(priority = 9, description = "Give TATdate for FastTrack and ensure the same schedule can be copied to General")
    public void verifyFastTrackToGeneral() {

        List<String> actualValues = addJournalPage.FastToGenCopyTat();

        List<String> expectedValues = List.of("1", "2", "3", "4", "5", "6", "7", "8",
                "9", "1",
                "2", "3", "4",
                "5", "6",
                "7", "8", "9");


        Assert.assertEquals(actualValues, expectedValues, "Values retrieved from page class do not match expected values");

        ExtentReportListener.getTest().log(Status.INFO, "Values are imported from FastTrack to General");


    }

    @Test(priority = 10, description = "After Copied, verify the TAT modification (General to FastTrack) be possible separately")
    public void verifyTATModificationGeneralToFast() {


        ExtentReportListener.getTest().log(Status.INFO, "Check modification of TAT after Copied (Gen to Fast)");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 3);

        for (Object[] row : excelDataA) {

            if (row.length == 4) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();


                List<String> actualValues = addJournalPage.TATModificationGeneralToFastTrack(Pub, J_acrm, J_name, PubName);

                List<String> expectedValues = List.of("1", "1", "1", "1", "1", "1", "1", "1",
                        "1", "1",
                        "1", "1", "1",
                        "1", "1",
                        "1", "1", "1");

                Assert.assertEquals(actualValues, expectedValues, "Values retrieved from page class do not match expected values");

                ExtentReportListener.getTest().log(Status.INFO, "Edited Values for imported FastTrack days has been displayed ");


            }
        }
    }

    @Test(priority = 11, description = "After Copied, verify the TAT modification (FastTrack to General) be possible separately")
    public void verifyTATModificationFastTrackToGeneral() {


        ExtentReportListener.getTest().log(Status.INFO, "Check modification of TAT after Copied (Fast to Gen)");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 3);

        for (Object[] row : excelDataA) {

            if (row.length == 4) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();


                List<String> actualValues = addJournalPage.TATModificationFastTrackToGeneral(Pub, J_acrm, J_name, PubName );


                List<String> expectedValues = List.of("2", "2", "2", "2", "2", "2", "2", "2",
                        "2", "2",
                        "2", "2", "2",
                        "2", "2",
                        "3", "3", "3");

                Assert.assertEquals(actualValues, expectedValues, "Values retrieved from page class do not match expected values");


                ExtentReportListener.getTest().log(Status.INFO, "Edited Values for imported General days has been displayed ");


            }
        }
    }

    @Test(priority = 11, description = "Import TAT from Publisher & verify the details are correctly imported for Gen & FastTat")
    public void verifyImportTATFromPub_GenToFast() {

        ExtentReportListener.getTest().log(Status.INFO, "Import Publisher TAT to Gen to Fast and verify it");

        List<Object[]> excelData = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 5);

        for (Object[] row : excelData) {

            if (row.length == 4) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();

                List<String> actualValues = addJournalPage.TATImportFromGenToFast(Pub, J_acrm, J_name, PubName, J_name, J_acrm);

                List<String> expectedValues = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9");

                Assert.assertEquals(actualValues, expectedValues, "Values retrieved from page class do not match expected values");


                ExtentReportListener.getTest().log(Status.INFO, "Verified Gen to fast track import values");


            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }

        }


    }

    @Test(priority = 12, description = "Import TAT from Publisher & verify the details are correctly imported for Fast & GenTat")
    public void verifyImportTATFromPub_FastToGen() {

        ExtentReportListener.getTest().log(Status.INFO, "Import Publisher TAT to Fast to Gen and verify it");

        List<Object[]> excelData = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 5);

        for (Object[] row : excelData) {

            if (row.length == 4) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();

                List<String> actualValues = addJournalPage.TATImportFromFastToGen(Pub, J_acrm, J_name, PubName, J_name, J_acrm);

                List<String> expectedValues = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9");

                Assert.assertEquals(actualValues, expectedValues, "Values retrieved from page class do not match expected values");


                ExtentReportListener.getTest().log(Status.INFO, "Verified fast to gen import values");


            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }

        }


    }

    @DataProvider(name = "getJrNewData")
    public Object[][] getJournalNewData() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 6);
    }

    @Test(priority = 12, dataProvider = "getJrNewData", description = "JMS-175 : Move the files from Latest to Archive and save the journal and verify the number of files later ")
    public void verifyFromLatestToArchFiles(String Arch_Sty) {


        ExtentReportListener.getTest().log(Status.INFO, "Import Publisher TAT to Fast to Gen and verify it");

        List<Object[]> excelData = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 5);

        for (Object[] row : excelData) {

            if (row.length == 4) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();

                System.out.println("for: " + Pub);
                System.out.println("for: " + J_acrm);
                System.out.println("for: " + J_name);
                System.out.println("for: " + PubName);


                String Arch_File = addJournalPage.moveLatestToArchiveAndVerify(Pub, J_acrm, J_name, PubName, Arch_Sty);
                System.out.println("Archive File received as: " + Arch_File);

                Assert.assertEquals(Arch_File, Arch_Sty, "Latest Files moved to archived files");

                ExtentReportListener.getTest().log(Status.INFO, "Archived file verified");


            } else {

                System.out.println("Row does not have expected numbers: " + row.length);

            }


        }

    }

    @DataProvider(name = "getJrNewData2")
    public Object[][] getJournalNewData2() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 7);
    }

    @Test(priority = 13, dataProvider = "getJrNewData2", description = "JMS-169 : Ensure integrity - Before Journal creation, Try to change Publisher ")
    public void verifyChangePubBeforeCreateJr(String PubNewAckro, String PubNameNew2) {

        ExtentReportListener.getTest().log(Status.INFO, "Enter Same journal with one publisher");

        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 8);

        for (Object[] row : excelDataA) {

            if (row.length == 3) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();

                System.out.println("for : " + Pub);
                System.out.println("for : " + J_acrm);
                System.out.println("for : " + J_name);

                addJournalPage.JrCreationByChangingPub(Pub, J_acrm, J_name, PubNewAckro, PubNameNew2);
                Assert.assertTrue(true, "Journal not added once we change new publisher");

            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }


        }
    }

    @Test(priority = 14, description = "JMS-171: (Publisher has some files) Create a journal under publisher without importing files\n")
    public void verifyJournalWithoutPubImport() {

        ExtentReportListener.getTest().log(Status.INFO, "verifying publiSher is added");

        List<Object[]> excelData = ExcelParser.getExcelData("D:\\ZoneTest\\AddPub.xlsx", 0);

        for (Object[] row : excelData) {
            if (row.length == 8) {
                String acro = row[0].toString();
                String pub = row[1].toString();
                String c = row[2].toString();
                String d = row[3].toString();
                String e = row[4].toString();
                String f = row[5].toString();
                String g = row[6].toString();
                String h = row[7].toString();


                addJournalPage.addPublisher(acro, pub, c, d, e, f, g, h);


            } else {
                System.out.println("Row does not have the expected number of columns: " + row.length);
            }

        }
        ExtentReportListener.getTest().log(Status.INFO, "New publisher is added");


        ExtentReportListener.getTest().log(Status.INFO, "verifying the error message");
        List<Object[]> excelDataAB = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 9);

        for (Object[] row : excelDataAB) {
            if (row.length == 3) {
                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();


                addJournalPage.createJrWithoutImportFiles(Pub, J_acrm, J_name);
                Assert.assertTrue(true, "Error message has been displayed to import files");
                ExtentReportListener.getTest().log(Status.INFO, "Journal cant be added without files has been verified");


            }


        }

    }

    @Test(priority = 15, description = "JMS-153 : Create and verify the Journal with valid details")
    public void verifyAddJournals() {

        ExtentReportListener.getTest().log(Status.INFO, "Enter valid journal credentials");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 10);

        for (Object[] row : excelDataA) {

            if (row.length == 4) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();

                System.out.println("for : " + Pub);
                System.out.println("for : " + J_acrm);
                System.out.println("for : " + J_name);
                System.out.println("for : " + PubName);


                addJournalPage.checkJournalAdded(Pub, J_acrm, J_name, PubName);
                Assert.assertTrue(true, "Journal not added once we change new publisher");
                ExtentReportListener.getTest().log(Status.INFO, "Journal added is verified");


            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }


        }


    }


    @DataProvider(name = "getJrNewData3")
    public Object[][] getJournalNewData3() throws IOException {

        return ReadExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 11);
    }


    @Test(priority = 16, dataProvider = "getJrNewData3", description = "JMS-156 : Journal can't be duplicated within the publisher")
    public void verifyJrCantDuplicatedWithPub(String Pub, String J_acrm, String J_name, String PubName) {


        ExtentReportListener.getTest().log(Status.INFO, "Check whether journal can't be duplicated");
        List<Boolean> JrVisible = addJournalPage.JournalCantDuplicated(Pub, J_acrm, J_name, PubName);


//        boolean isJrvisible = JrVisible1.get(0);
//        System.out.println(isJrvisible);
//
//        boolean isJrvisible1 = JrVisible1.get(1);
//        System.out.println(isJrvisible1);
//
//        boolean isJrvisible2 = JrVisible1.get(2);
//        System.out.println(isJrvisible2);


        SoftAssert softAssert = new SoftAssert();

        for (Boolean isVisible : JrVisible) {
            softAssert.assertTrue(isVisible, "Expected journal to be visible but it's not.");
        }

        softAssert.assertAll();

    }

    @Test(priority = 17, description = "JMS-170 : Even if the publisher has files, it should not be imported unless IMPORT icon is clicked")
    public void verifyImportIconForImportFiles() {

        ExtentReportListener.getTest().log(Status.INFO, "Check whether files has been imported before import icon");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 12);


        for (Object[] row : excelDataA) {

            if (row.length == 3) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();

                System.out.println("for : " + Pub);
                System.out.println("for : " + J_acrm);
                System.out.println("for : " + J_name);


                List<Boolean> ImpIconClickFiles = addJournalPage.ImportIconClickWithoutFiles(Pub, J_acrm, J_name);

                SoftAssert softAssert = new SoftAssert();

                boolean ImpIconSty = ImpIconClickFiles.get(0);
                softAssert.assertFalse(ImpIconSty, "Files has been displayed before import icon");
                System.out.println(ImpIconSty);

                boolean ImpIconSty_1 = ImpIconClickFiles.get(1);
                softAssert.assertTrue(ImpIconSty_1, "Files not displayed even after import icon");
                System.out.println(ImpIconSty_1);

                boolean ImpIconGuide = ImpIconClickFiles.get(2);
                softAssert.assertFalse(ImpIconGuide, "Files has been displayed before import icon");
                System.out.println(ImpIconGuide);

                boolean ImpIconGuide_1 = ImpIconClickFiles.get(3);
                softAssert.assertTrue(ImpIconGuide_1, "Files has been displayed before import icon");
                System.out.println(ImpIconGuide_1);

                ExtentReportListener.getTest().log(Status.INFO, "Import icon verified for uploading files");
            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }


        }

    }

    @Test(priority = 18, description = "JMS-165 : Select General TAT for journal and verify the same by creating new article (Add Article)")
    public void verifyGenTATByAddingArticle() {

        ExtentReportListener.getTest().log(Status.INFO, "Check whether files has been imported before import icon (Gen to Fast)");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 13);


        for (Object[] row : excelDataA) {

            if (row.length == 3) {

                String artname = row[0].toString();


                List<String> GenTATVerify = addJournalPage.genTATverifyByAddArticle(artname);
                List<String> expectedValues = List.of("1", "1", "1", "1", "1", "1", "1", "1", "1",
                        "1", "1", "1", "1", "1", "1", "1", "1", "1");

                SoftAssert softAssert = new SoftAssert();

                softAssert.assertEquals(GenTATVerify, expectedValues, "Values retrieved from page class do not match expected values");
                ExtentReportListener.getTest().log(Status.INFO, "Import icon verified for uploading files in add article");
            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }

        }

    }

    @Test(priority = 18, description = "JMS-166 : Select Fast TAT for journal and verify the same by creating new article (Add Article)")
    public void verifyFastTATByAddingArticle() {

        ExtentReportListener.getTest().log(Status.INFO, "Check whether files has been imported before import icon (Fast to Gen)");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 13);


        for (Object[] row : excelDataA) {

            if (row.length == 1) {

                String artname = row[0].toString();


                List<String> FastTATVerify = addJournalPage.fastTATverifyByAddArticle(artname);
                List<String> expectedValues = List.of("1", "1", "1", "1", "1", "1", "1", "1", "1",
                        "1", "1", "1", "1", "1", "1", "1", "1", "1");

                SoftAssert softAssert = new SoftAssert();

                softAssert.assertEquals(FastTATVerify, expectedValues, "Values retrieved from page class do not match expected values");
                ExtentReportListener.getTest().log(Status.INFO, "Import icon verified for uploading files in add article (Fast to Gen)");
            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }

        }


    }

    @Test(priority = 19, description = " JMS-172 : Import template from publisher and verify the following details from publisher itself")
    public void verifyImportStyTemplateFromPub() throws InterruptedException {


        ExtentReportListener.getTest().log(Status.INFO, "Check the import from publisher style template and verify same");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 12);
        for (Object[] row : excelDataA) {

            if (row.length == 3) {

                String Pub = row[0].toString();
                String j_acrm = row[1].toString();
                String j_name = row[2].toString();


                addJournalPage.ImportStyFromPub(Pub, j_acrm, j_name);
                int latestFileCount = addJournalPage.getFileCount();
                int expectedFileCount = 1;
                System.out.println("Actual Latest files count: "+latestFileCount);
                Assert.assertEquals(latestFileCount, expectedFileCount, "The number of files does not match the expected count.");


                List<String> latestFilesTexts = addJournalPage.getFileTexts();
                List<String> expectedTexts = List.of("ems_journal.sty");

                System.out.println("Actual lastName: "+latestFilesTexts);
                Assert.assertTrue(latestFilesTexts.containsAll(expectedTexts), "The list of file texts does not match the expected texts.");
                ExtentReportListener.getTest().log(Status.INFO, "Verification done for Style template");

            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }


        }


    }


    @Test(priority = 20, description = "JMS-173 : Import GuideLines from publisher and verify the following details")
    public void verifyImportGuideLineFromPub(){


        ExtentReportListener.getTest().log(Status.INFO, "Check the import from publisher Guidelines and verify same");
        List<Object[]> excelDataA = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 12);
        for (Object[] row : excelDataA) {

            if (row.length == 3) {

                String Pub = row[0].toString();
                String j_acrm = row[1].toString();
                String j_name = row[2].toString();


                addJournalPage.ImportGuideFromPub(Pub, j_acrm, j_name);
                int latestguideFileCount = addJournalPage.getGuideFileCount();
                int expectedFileCount = 1;
                System.out.println("Actual Latest files count: "+latestguideFileCount);
                Assert.assertEquals(latestguideFileCount, expectedFileCount, "The number of files does not match the expected count.");


                List<String> latestGuideFilesTexts = addJournalPage.getGuideFileTexts();
                List<String> expectedTexts = List.of("GuidelinesNew.docx");

                System.out.println("Actual lastName: "+latestGuideFilesTexts);
                Assert.assertTrue(latestGuideFilesTexts.containsAll(expectedTexts), "The list of file texts does not match the expected texts.");
                ExtentReportListener.getTest().log(Status.INFO, "Verification done for GuideLine doc");




            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }


        }




    }


    @Test(priority = 21, description = "JMS-164 : Verify modification of TAT - Create two journals, import TAT for both journals (Add article) - Modified")
    public void verifyModifiedTATtwoJournalAddAritcle() throws InterruptedException {

        ExtentReportListener.getTest().log(Status.INFO, "Check the modification of TAT by adding 2 journals from add article - Modifed TAT");

        List<Object[]> excelData = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 14);

        for (Object[] row : excelData) {

            if (row.length == 43) {

                String Pub = row[0].toString();
                String J_acrm = row[1].toString();
                String J_name = row[2].toString();
                String PubName = row[3].toString();
                String a = row[4].toString();
                String b = row[5].toString();
                String c = row[6].toString();
                String d = row[7].toString();
                String e = row[8].toString();
                String f = row[9].toString();
                String g = row[10].toString();
                String h = row[11].toString();
                String i = row[12].toString();
                String j = row[13].toString();
                String k = row[14].toString();
                String l = row[15].toString();
                String m = row[16].toString();
                String n = row[17].toString();
                String o = row[18].toString();
                String p = row[19].toString();
                String q = row[20].toString();
                String r = row[21].toString();
                String s = row[22].toString();
                String t = row[23].toString();
                String u = row[24].toString();
                String v = row[25].toString();
                String w = row[26].toString();
                String x = row[27].toString();
                String y = row[28].toString();
                String z = row[29].toString();
                String aa = row[30].toString();
                String bb = row[31].toString();
                String cc = row[32].toString();
                String dd = row[33].toString();
                String ee = row[34].toString();
                String ff = row[35].toString();
                String gg = row[36].toString();
                String hh = row[37].toString();
                String ii = row[38].toString();
                String jj = row[39].toString();
                String artName = row[40].toString();
                String journalacro = row[41].toString();
                String workflow = row[42].toString();


                List<String> actualValuesModifiedTAT = addJournalPage.modificationOfTATbyAddArticleTwoJournals(Pub, J_acrm, J_name, PubName, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o,
                        p, q, r, s, t, u, v, w, x, y, z, aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, artName, journalacro, workflow);

                List<String> expectedValuesModifiedTAT = List.of(a, b, c, d, e, f, g, h, i,
                        j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, aa, bb, cc, dd, ee, ff, gg, hh, ii, jj);

                Assert.assertEquals(actualValuesModifiedTAT, expectedValuesModifiedTAT, "Values retrieved from page class do not match expected values");


                ExtentReportListener.getTest().log(Status.INFO, "Modified TAT for journals has been verified from add articles");


            } else {

                System.out.println("Row does not have expected numbers: " + row.length);
            }

        }
    }

        @Test(priority = 22, description = "JMS-164 : Verify unmodified of TAT - Create two journals, import TAT for both journals (Add article) - Modified")
        public void verifyUnModifiedTATtwoJournalAddAritcle() throws InterruptedException {


            ExtentReportListener.getTest().log(Status.INFO, "Check the modification of TAT by adding 2 journals from add article - Modifed TAT");

            List<Object[]> excelData = ExcelParser.getExcelData("D:\\ZoneTest\\newaddjournals.xlsx", 15);

            for (Object[] row : excelData) {

                if (row.length == 7) {

                    String Pub = row[0].toString();
                    String J_acrm = row[1].toString();
                    String J_name = row[2].toString();
                    String PubName = row[3].toString();
                    String artName = row[4].toString();
                    String journalacro = row[5].toString();
                    String workflow = row[6].toString();


                    List<String> actualValuesUnModifiedTAT = addJournalPage.unmodifiedTATbyAddArticleTwoJournals(Pub, J_acrm, J_name, PubName, artName, journalacro, workflow);

                    List<String> expectedValuesUnModifiedTAT = List.of("1", "1", "1", "1", "1", "1", "1", "1", "1",
                                                                                "1", "1", "1", "1", "1", "1", "1", "1", "1",
                                                                                 "1", "1", "1", "1", "1", "1", "1", "1","1",
                                                                                    "1", "1", "1", "1", "1", "1", "1");

                    Assert.assertEquals(actualValuesUnModifiedTAT, expectedValuesUnModifiedTAT, "Values retrieved from page class do not match expected values");


                    ExtentReportListener.getTest().log(Status.INFO, "UnModified TAT for journals has been verified from add articles");


                } else {

                    System.out.println("Row does not have expected numbers: " + row.length);
                }

            }
        }




}






















