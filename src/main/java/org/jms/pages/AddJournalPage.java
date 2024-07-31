package org.jms.pages;


import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AddJournalPage {

    private Page page;
    FileChooser fileChooser;
    private List<String> latestFilesTexts = new ArrayList<>();
    private List<String> guideDocFilesTexts = new ArrayList<>();
    private int latestFilesCount;
    private int guideDocFilesCount;


    private String baseicon = "//img[@src='/jms/src/assets/GeneralIcons/shortcuts.svg']";
    private String addjouricon = "id=add_journal";
    private String addJournalPage = "(//*[text()='Add Journal'])[1]";


    private String publisher_1 = "id=publisher";
    private String publisher_2 = "(//p[normalize-space()='EMS Press'])";
    private String jor_acrm = "//input[contains(@data-testid,'journal-acronym')]";
    private String jor_fullname = "//input[@data-testid='journal-full-name']";
    private String jor_received_date = "//input[@data-testid='journal-received-date']";
    private String recto_facing = "//input[@type='checkbox']/..";
    private String layout_1 = "id=layout";
    private String layout_2 = "//*[text()='Single']";
    private String trimSizeWidth = "//input[@data-testid='trim-size-width']";

    private String trimSizeHeight = "//input[@data-testid='trim-size-height']";
    private String remarks = "id=remarks";
    private String category_1 = "//label[text()='Category']/../input";
    private String category_2_typeSetting = "//p[normalize-space()='Typesetting']";
    private String category_2_technicalEdit = "//p[normalize-space()='Technical Editing']";
    private String category_3_CopyEdit = "//p[normalize-space()='Copy Editing']";
    private String copyEditLevel_1 = "id=copyEditingLevel";
    private String copyEditLevel_2 = "//p[@for='L1']";
    private String pubType_1 = "//label[text()='Publishing Type']/../input";
    private String pubType_CheckAll = "//div[text()='Check All']";

    //--General--//

    private String G_FPdaysOfLatex = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Latex Normalization']/../input";
    private String G_FPdaysOfGraphics = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Graphics']/../input";
    private String G_FPdaysOfPreEdit = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Pre-Editing']/../input";
    private String G_FPdaysOfCopyEdit = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Copyediting']/../input";
    private String G_FPdaysOfTypeSetting = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Typesetting']/../input";
    private String G_FPdaysOfQC = "//*[text()='General']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for QC']/../input";

    private String G_AUdaysOfPage = "//*[text()='General']/..//*[text()='TAT for AU Revises ']/../..//*[text()='Days for Pagination']/../input";
    private String G_AUdaysOfQC = "//*[text()='General']/..//*[text()='TAT for AU Revises ']/../..//*[text()='Days for QC']/../input";
    private String G_PEdaysOfPage = "//*[text()='General']/..//*[text()=' TAT for PE Revises ']/../..//*[text()='Days for Pagination']/../input";
    private String G_PEdaysOfQC = "//*[text()='General']/..//*[text()=' TAT for PE Revises ']/../..//*[text()='Days for QC']/../input";

    private String G_ONFdaysOfPage = "//*[text()='General']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for Pagination']/../input";
    private String G_ONFdaysOfQC = "//*[text()='General']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for QC']/../input";
    private String G_ONFdaysOfXML = "//*[text()='General']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for XML']/../input";

    private String G_ISSdaysOfPage = "//*[text()='General']/..//*[text()=' TAT for Issue']/../..//*[text()='Days for Pagination']/../input";
    private String G_ISSdaysOfQC = "//*[text()='General']/..//*[text()=' TAT for Issue']/../..//*[text()='Days for QC']/../input";

    private String G_PrintdaysOfPage = "//*[text()='General']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for Pagination']/../input";
    private String G_PrintdaysOfQC = "//*[text()='General']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for QC']/../input";
    private String G_PrintdaysOfXML = "//*[text()='General']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for XML']/../input";


    //--FastTrack--//

    private String FS_FPdaysOfLatex = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Latex Normalization']/../input";
    private String FS_FPdaysOfGraphics = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Graphics']/../input";
    private String FS_FPdaysOfPreEdit = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Pre-Editing']/../input";
    private String FS_FPdaysOfCopyEdit = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Copyediting']/../input";
    private String FS_FPdaysOfTypeSetting = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for Typesetting']/../input";
    private String FS_FPdaysOfQC = "//*[text()='Fasttrack']/..//*[text()='TAT for First Proof']/../..//*[text()='Days for QC']/../input";

    private String FS_AUdaysOfPage = "//*[text()='Fasttrack']/..//*[text()='TAT for AU Revises ']/../..//*[text()='Days for Pagination']/../input";
    private String FS_AUdaysOfQC = "//*[text()='Fasttrack']/..//*[text()='TAT for AU Revises ']/../..//*[text()='Days for QC']/../input";
    private String FS_PEdaysOfPage = "//*[text()='Fasttrack']/..//*[text()=' TAT for PE Revises ']/../..//*[text()='Days for Pagination']/../input";
    private String FS_PEdaysOfQC = "//*[text()='Fasttrack']/..//*[text()=' TAT for PE Revises ']/../..//*[text()='Days for QC']/../input";

    private String FS_ONFdaysOfPage = "//*[text()='Fasttrack']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for Pagination']/../input";
    private String FS_ONFdaysOfQC = "//*[text()='Fasttrack']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for QC']/../input";
    private String FS_ONFdaysOfXML = "//*[text()='Fasttrack']/..//*[text()=' TAT for Online First ']/../..//*[text()='Days for XML']/../input";

    private String FS_ISSdaysOfPage = "//*[text()='Fasttrack']/..//*[text()=' TAT for Issue']/../..//*[text()='Days for Pagination']/../input";
    private String FS_ISSdaysOfQC = "//*[text()='Fasttrack']/..//*[text()=' TAT for Issue']/../..//*[text()='Days for QC']/../input";

    private String FS_PrintdaysOfPage = "//*[text()='Fasttrack']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for Pagination']/../input";
    private String FS_PrintdaysOfQC = "//*[text()='Fasttrack']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for QC']/../input";
    private String FS_PrintdaysOfXML = "//*[text()='Fasttrack']/..//*[text()=' TAT for Print/Web ']/../..//*[text()='Days for XML']/../input";

    //------Forward & Backward Button------//

    private String general_to_fast = "(//img[@title='Replicate values from General to Fasttrack'])[1]";
    private String fast_to_general = "(//img[@title='Replicate values from Fasttrack to General'])[1]";
    private String importCopyTAT = "//div[@class='_content_1lcct_45']//following::newbutton[text()='Yes']";

    //----Style Template----//

    private String styleTemplateUploadButton = "//*[@id='styles-upload']/../div";

    //----Guideline Document----//

    private String guideLineUploadButton = "//*[@id='guidelines-upload']/../div";

    //---Import Operations---//

    private String importPubTAT_button = "(//div[@title='Import tats from Publisher'])[1]";
    private String importPubTATyes = "//*[text()='Yes']";
    private String importStyleTemplate = "//div[@class='_style-editor-container_132u2_451']//div[@title='Import from Publisher']";
    private String importGuideLinesDocument = "//div[@class='_guidelines-container_132u2_583']//div[@title='Import from Publisher']";
    private String importFileTATyes = "//*[text()='Yes']";

    private String addButton = "//button[@type='button']";

    private String acknowledgement = "//div[contains(text(),'Journal Added Successfully')]";

    private String alertCloseButton = "//h2[text()='JMS - Add Journal']//following::span[1]";
    private String manageMenu = "//div[@id='root']//following::p[text()='Manage']";

    private String JourView_1 = "id=select_view";
    private String JourView_2 = "//p[@for='Journals View']";
    private String EditJournal = "//*[text()='Edit Journal']";

    private String LatestToArch_Yes = "//*[text()='Move File']//following::div//following::newbutton[text()='Yes']";

    private String manageMenu_Art = "//div[@id='root']//following::p[text()='Manage']";

    private String ArtView_1 = "id=select_view";
    private String ArtView_2 = "//p[@for='Articles View']";

    public String Publisher1;
    public String Pub1_SameJrAck;
    public String Pub1_SameJrName;
    public String Publisher2;
    public String Pub2_SameJrAck;
    public String Pub2_SameJrName;

    private String addpubicon = "id=add_publisher";


    private String pub_acronym = "//input[@data-testid='publisher-acronym']";
    private String pub_name_textbox = "//input[@data-testid='publisher-name']";
    private String pub_mail_textbox = "//input[@data-testid='email-account']";
    private String desc_inputbox = "//*[@name='description']";
    private String selectdateinput = "//input[@type='date']";
    private String pub_location = "//input[@data-testid='publisher-location']";
    private String ftpusername = "//input[@data-testid='ftp-user-name']";
    private String ftppassword = "//input[@data-testid='ftp-password']";
    private String ftp_initial_directory = "//input[@data-testid='ftp-initial-directory']";

    //General for Add article
    private String daysforlatexnormalization = "//*[text()='TAT for First Proof']/../..//input[@id='daysForLaTeXNorFP']/../input";
    private String daysforgraphics = "//*[text()='TAT for First Proof']/../..//input[@id='daysForGraphicsFP']/../input";
    private String daysforPreediting = "//*[text()='TAT for First Proof']/../..//input[@id='daysForPreFP']/../input";
    private String daysforcopyediting = "//*[text()='TAT for First Proof']/../..//input[@id='daysForCopyFP']/../input";
    private String daysfortypesettings = "//*[text()='TAT for First Proof']/../..//input[@id='daysForPaginationFP']/../input";
    private String daysforqc = "//*[text()='TAT for First Proof']/../..//input[@id='daysForQCFP']/../input";

    private String daysforaupag = "//*[text()='TAT for AU Revises']/../..//input[@id='daysForPaginationAU']/../input";
    private String daysforauqc = "//*[text()='TAT for AU Revises']/../..//input[@id='daysForQCAU']/../input";

    private String daysforpepag = "//*[text()='TAT for PE Revises']/../..//input[@id='daysForPaginationPE']/../input";
    private String daysforpeqc = "//*[text()='TAT for PE Revises']/../..//input[@id='daysForQCPE']/../input";

    private String daysforonlinepag = "//*[text()='TAT for Online First']/../..//input[@id='hoursDaysForPagination']/../input";
    private String daysforonlineqc = "//*[text()='TAT for Online First']/../..//input[@id='hoursDaysForQC']/../input";
    private String daysforonlinexml = "//*[text()='TAT for Online First']/../..//input[@id='hoursDaysForXML']/../input";

    private String daysforIssuePag = "//*[text()='TAT for Issue']/../..//input[@id='daysForPaginationIssue']/../input";
    private String daysforIssueQC = "//*[text()='TAT for Issue']/../..//input[@id='daysForQCIssue']/../input";

    private String daysforprintpag = "//*[text()='TAT for Print/Web']/../..//input[@id='daysForPaginationWeb']/../input";
    private String daysforprintQC = "//*[text()='TAT for Print/Web']/../..//input[@id='daysForQCWeb']/../input";
    private String daysforprintxml = "//*[text()='TAT for Print/Web']/../..//input[@id='daysForXMLWeb']/../input";

    private String addbutton = "//*[@data-testid='submit-button']";
    private String Imageuploadbutton = "//button[text()='Upload Image']";

    private String styletemplate = "//*[@id='styles-upload']//preceding::div[1]";

    private String guidelinesdoc = "//*[@id='guidelines-upload']//preceding::div[1]";
    private String addalertclose = "//h2[text()='JMS - Add Publisher']//following::span[1]";
    private String selectPubJrName = "id=publisher";
    private String addarticleicon = "id=add_article";
    private String UploadLabel = "//label[text()='Upload']";
    private String formLabel = "//label[text()='Form']";
    private String clientftplabel = "//label[text()='Client FTP']";

    private String UpdateJournal = "//button[normalize-space()='Update Journal']";
    private String closeUpdateJournal = "//h2[text()='JMS - Update Journal']//following::span[1]";

    /* private String uploadform="//*[@alt='Option 1 (Upload)']";

     private String selectprioritydropdown="id=priority";
     private String selectpriority="//*[@id='priority']//following::p[text()='High']";
     private String selecttat="//label[text()='TAT']//following::div[1]";
     private String selecttatinput="//label[text()='TAT']//following::li[1]//following::p[1]";
     private String Doino="id=doi";
     private String  selectworkflowdropdown="id=workFlow";
     private String selectworkflow="//*[@id='workFlow']//following::p[text()='Fresh']";
     private String noofpages="//input[@id='numberOfPages']";
     private String cebypass="id=CE by-pass";*/
    private String form = "//img[@alt='Option 2 (Form)']";
    private String clickSelectPub = "id=publisher";
    private String Selectpubdropdown = "//input[@placeholder='Search...']";
    private String selectpublisher = "//p[normalize-space(text())='GGD(EMS Press)']\n";
    private String articleidinput = "id=articleID";
    private String authormail = "id=authorMail";
    private String authorname = "id=authorName";
    private String articlename = "id=articleName";
    private String selectpriority = "id=priority";
    private String selectpriorityopt = "//*[@id='priority']//following::p[text()='High']";
    private String receivedate = "id=receivedDate";
    private String reviseddate = "id=revisedDate";
    private String Accepteddate = "id=historyAccDate";
    private String selecttat = "//label[text()='TAT']//following::div[1]";
    private String selecttatinput = "//label[text()='TAT']//following::li[1]//following::p[1]";
    private String Doino = "id=doi";
    private String workflowselection = "//*[text()='Assign Workflow']";
    private String selectgeneralworkflow = "//*[@alt='General']";
    private String assignbutton = "//*[text()='Assign']";


    // private String selectworkflowopt="//*[@id='workFlow']//following::p[text()='Fresh']";
    private String noofpages = "//input[@id='numberOfPages']";
    private String cebypass = "id=CE by-pass";
    private String articletype = "//*[@placeholder='Enter Article Type...']";
    private String TATShow = "//*[text()='Turn Around Time']//preceding-sibling::img";
    private String importtatfromjournal = "//*[@title='Import TATs from Journal']";
    private String confirmimportfromjour = "//*[contains(text(),'Import TAT')]//following::div//following::newbutton[contains(text(),'Yes')]";
    private String trackType = "id=trackType";
    private String GenTrack = "//ul[@class='_options-lists_15e5e_211']//p[@for='General']";
    private String FastTrack = "//ul[@class='_options-lists_15e5e_211']//p[@for='FastTrack']";


    private String startdate = "id=startDate";
    private String ChecklistSelectionShow = "//*[text()='Checklist Selections']//preceding-sibling::img";

    private String OnOpenAccess = "id=acpOpenAccess";


    private String fileupload = "//*[@alt='Upload']";
    private String addnotes = "//*[@alt='Add Notes']";
    private String Plzwwritehere = "//*[@data-placeholder='Type here...']";
    private String AddNoteutton = "//*[text()='Add Note']";
    private String addnotetoastclose = "//*[text()='JMS - Add Notes']//following::span[1]";
    private String checklist = "//*[text()='Check Lists']";
    private String figurechecklist = "id=figures";
    private String checlistalert = "//*[text()='Alert']//following::newbutton[text()='Yes']";
    private String checklisttoast = "//*[text()='JMS - Check Lists']//following::span[1]";
    private String mailpreview = "//*[text()='Preview']";
    private String tomail = "//label[text()='To']//following::input[@id='category'][1]";
    private String tomailluser = "//p[normalize-space(text())='compuscriptrep@gmail.com']//preceding-sibling::input";
    private String Acknowledgementtomailluser = "//p[normalize-space(text())='latexam@gmail.com']//preceding-sibling::input";


    private String ccmail = "//label[text()='Cc']//following::input[@id='category'][1]";
    private String ccmailuser = "//p[normalize-space(text())='nirmala@pdmrindia.com']//preceding-sibling::input";
    private String Acknowlegeemtnsavemailbutton = "//button[text()='Save Mail']";
    private String Acknowledgementyesalert = "//*[text()='Are you sure to Save the mail on acknowledgement?']//following::newbutton[text()='Yes']";
    private String Acknowlegementtoastclose = "//*[text()='JMS - Mail']//following::span[1]";
    private String notificationmail = "//*[text()='Notification']";
    private String savenotificationmail = "//*[text()='Save Mail']";
    private String notificationalert = "//*[text()='Are you sure to Save the mail on notification?']//following::newbutton[text()='Yes']";
    private String notificationsuccesstoastmail = "//*[text()='A mail will be triggered once the article is added successfully']//preceding::span[text()='×']";
    private String addarticlebutton = "(//*[text()='Add Article'])[2]";
    private String checkall = "//div[text()='Check All']";
    private String Checklistsubmitbutton = "//button[text()='Submit CheckList']";
    private String addarticlealert = "//*[text()='JMS - Add Article']//following::span[1]";
    private String selectview = "id=select_view";
    private String journalsview = "//*[@id='select_view']//following::li//following::p[text()='Articles View']";

    private String supplement = "id=supplement";
    private String aopFree = "id=acpFree";
    private String aopopenacess = "id=acpOpenAccess";
    private String tables = "id=tables";
    private String displayfigures = "id=displayFigures";
    private String InlineFigures = "id=inlineFigures";
    private String CopyTat = "//*[@title='Replicate values from General to Fasttrack']";
    private String CopyTatConfirm = "//*[text()='Yes']";
    private String managemenu = "//div[@id='root']//following::p[text()='Manage']";

    //webelement for fasttrack

    private String f_LatexNormal = "//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='days-for-latex-normalization']";
    private String f_graphics = "//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-graphics']";
    private String f_preedit = "//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-pre-editing']";
    private String f_copyedit = "//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-copyediting']";

    private String f_ts = "//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-typesetting']";
    private String f_qc = "(//h2[text()='Fasttrack']//following::h3[text()='TAT for First Proof']//following::input[@data-testid='number-of-days-for-qc'][1])";
    private String f_au_pag = "//h2[text()='Fasttrack']//following::h3[text()='TAT for AU Revises ']//following::input[@data-testid='number-of-days-for-pagination'][1]";
    private String f_au_qc = "//h2[text()='Fasttrack']//following::h3[text()='TAT for AU Revises ']//following::input[@data-testid='number-of-days-for-qc'][1]";
    private String f_pe_pag = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for PE Revises ']//following::input[@data-testid='number-of-days-for-pagination'][1]";
    private String f_pe_qc = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for PE Revises ']//following::input[@data-testid='number-of-days-for-qc'][1]";
    private String f_o_pag = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-or-days-for-Pagination']";
    private String f_o_qc = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-or-days-for-QC']";
    private String f_o_xml = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for Online First ']//following::input[@data-testid='hours-days-for-xml']";
    private String f_iss_pag = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for Issue']//following::input[@data-testid='number-of-days-for-pagination'][1]";
    private String f_iss_qc = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for Issue']//following::input[@data-testid='number-of-days-for-qc'][1]";
    private String f_priweb_pag = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-pagination'][1]";
    private String f_printweb_qc = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-qc']";
    private String f_printweb_xml = "//h2[text()='Fasttrack']//following::h3[text()=' TAT for Print/Web ']//following::input[@data-testid='number-of-days-for-xml']";
    private String addjournalicon = "id=add_journal";


    public AddJournalPage(Page page) {
        this.page = page;
    }

    public void verifyAddJournalIconIsClickable() {

        page.locator(baseicon).click();
        page.locator(addjouricon).click();

    }


    public void generalAddJournals() {


        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        page.locator(jor_received_date).fill(formattedDate);
        page.locator(recto_facing).click();
        page.locator(layout_1).click();
        page.locator(layout_2).click();
        page.locator(trimSizeWidth).fill("1");
        page.locator(trimSizeHeight).fill("2");
        page.locator(remarks).fill("Test Remarks");
        page.locator(category_1).click();
        page.locator(category_2_technicalEdit).click();
        page.locator(category_2_typeSetting).click();
        page.locator(category_3_CopyEdit).click();
        page.locator(copyEditLevel_1).click();
        page.locator(copyEditLevel_2).click();
        page.locator(pubType_1).click();
        page.locator(pubType_CheckAll).click();

        page.locator(G_FPdaysOfLatex).fill("1");
        page.locator(G_FPdaysOfGraphics).fill("1");
        page.locator(G_FPdaysOfPreEdit).fill("1");
        page.locator(G_FPdaysOfCopyEdit).fill("1");
        page.locator(G_FPdaysOfTypeSetting).fill("1");
        page.locator(G_FPdaysOfQC).fill("1");

        page.locator(FS_FPdaysOfLatex).fill("1");
        page.locator(FS_FPdaysOfGraphics).fill("1");
        page.locator(FS_FPdaysOfPreEdit).fill("1");
        page.locator(FS_FPdaysOfCopyEdit).fill("1");
        page.locator(FS_FPdaysOfTypeSetting).fill("1");
        page.locator(FS_FPdaysOfQC).fill("1");

        page.evaluate("window.scrollBy(0, 320)");
        page.locator(G_AUdaysOfPage).fill("1");
        page.locator(G_AUdaysOfQC).fill("1");
        page.locator(FS_AUdaysOfPage).fill("1");
        page.locator(FS_AUdaysOfQC).fill("1");

        page.locator(G_PEdaysOfPage).fill("1");
        page.locator(G_PEdaysOfQC).fill("1");
        page.locator(FS_PEdaysOfPage).fill("1");
        page.locator(FS_PEdaysOfQC).fill("1");
        page.evaluate("window.scrollBy(0, 320)");

        page.locator(G_ONFdaysOfPage).fill("1");
        page.locator(G_ONFdaysOfQC).fill("1");
        page.locator(G_ONFdaysOfXML).fill("1");
        page.locator(FS_ONFdaysOfPage).fill("1");
        page.locator(FS_ONFdaysOfQC).fill("1");
        page.locator(FS_ONFdaysOfXML).fill("1");


        page.locator(G_ISSdaysOfPage).fill("2");
        page.locator(G_ISSdaysOfQC).fill("2");
        page.locator(FS_ISSdaysOfPage).fill("2");
        page.locator(FS_ISSdaysOfQC).fill("2");

        page.locator(G_PrintdaysOfPage).fill("2");
        page.locator(G_PrintdaysOfQC).fill("2");
        page.locator(G_PrintdaysOfXML).fill("2");
        page.locator(FS_PrintdaysOfPage).fill("2");
        page.locator(FS_PrintdaysOfQC).fill("2");
        page.locator(FS_PrintdaysOfXML).fill("2");

        fileChooser = page.waitForFileChooser(() -> page.locator(styleTemplateUploadButton).click());
        fileChooser.setFiles(Paths.get("ems_journal.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(guideLineUploadButton).click());
        fileChooser.setFiles(Paths.get("EMS_Press.pdf"));


    }


    public void generalPubAcroWithJname(String Pub, String J_acrm, String J_name) {

        page.locator(baseicon).click();
        page.locator(addjouricon).click();
        page.locator(publisher_1).click();
        page.locator("//div[@class='_options-container_15e5e_189']//following::ul/li//p[text()='" + Pub + "']").click();
        page.locator(jor_acrm).fill(J_acrm);
        page.locator(jor_fullname).fill(J_name);
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        page.locator(jor_received_date).fill(formattedDate);
        page.locator(recto_facing).click();
        page.locator(layout_1).click();
        page.locator(layout_2).click();
        page.locator(trimSizeWidth).fill("1");
        page.locator(trimSizeHeight).fill("2");
        page.locator(remarks).fill("Test New Remarks");
        page.locator(category_1).click();
        page.locator(category_2_technicalEdit).click();
        page.locator(category_2_typeSetting).click();
        page.locator(category_3_CopyEdit).click();
        page.locator(copyEditLevel_1).click();
        page.locator(copyEditLevel_2).click();
        page.locator(pubType_1).click();
        page.locator(pubType_CheckAll).click();


    }

    public void editJournals(String PubName, String J_acrm, String J_name) {
        System.out.println(PubName);
        Locator editJournalOption =
                page.locator("//h2[text()='" + PubName + "']//following::th[text()='" + J_acrm + "']//following::div[text()='" + J_name + "']//following::span[@data-target='#dropright'][1]");

        editJournalOption.scrollIntoViewIfNeeded();
        editJournalOption.click();
        page.waitForSelector(EditJournal).click();

    }

    public String sameAcrWithSlChar(String Pub, String J_acrm, String J_name) {

        page.locator(baseicon).click();
        page.locator(addjouricon).click();
        page.locator(publisher_1).click();
        page.locator("//div[@class='_options-container_15e5e_189']//following::ul/li//p[text()='" + Pub + "']").click();
        page.locator(jor_acrm).fill(J_acrm);
        page.locator(jor_fullname).fill(J_name);

        generalAddJournals();

        page.locator(addButton).click();

        String Add_alert = page.locator(acknowledgement).innerText();
        System.out.println("Acknowledgment is: " + Add_alert);
        page.locator(alertCloseButton).click();


        return Add_alert;


    }

    public String fromManageJournalsCheck(String J_acrm) {


        page.locator(manageMenu).click();
        page.locator(JourView_1).click();
        page.locator(JourView_2).click();

        String JValue = page.locator("//th[text()='" + J_acrm + "']").innerText();
        System.out.println("Journal Acroname: " + JValue);
        return JValue;

    }

    public Boolean diffPubSameJournals(String PubAcro1, String J_acrm, String J_name, String PubAcro2,
                                       String PubName, String PubNameII) {


        page.locator(publisher_1).click();
        page.locator("//div[@class='_options-container_15e5e_189']//following::ul/li//p[text()='" + PubAcro1 + "']").click();
        page.locator(jor_acrm).fill(J_acrm);
        page.locator(jor_fullname).fill(J_name);

        generalAddJournals();

        page.locator(addButton).click();

        String Add_alert = page.locator(acknowledgement).innerText();
        System.out.println("Acknowledgment is: " + Add_alert);

        page.locator(alertCloseButton).click();
        page.reload();


        page.locator(baseicon).click();
        page.waitForTimeout(1000);

        page.locator(addjouricon).click();
        page.locator(publisher_1).click();
        page.locator("//div[@class='_options-container_15e5e_189']//following::ul/li//p[text()='" + PubAcro2 + "']").click();
        page.locator(jor_acrm).fill(J_acrm);
        page.locator(jor_fullname).fill(J_name);

        generalAddJournals();

        page.waitForTimeout(1000);

//        String script = "document.body.style.zoom = '50%'";
        page.evaluate("window.scrollBy(0, 1000)");
//        page.evaluate(script);

        Locator Add = page.locator(addButton);
        boolean isVisible = Add.first().isVisible();
        System.out.println("is Add button visible? : " + isVisible);

        page.waitForSelector("//button[@type='button']");
        Add.click();
        page.locator(alertCloseButton).click();

        page.locator(manageMenu).click();
        page.locator(JourView_1).click();
        page.locator(JourView_2).click();
        page.waitForTimeout(1000);


        Locator Publisher1 = page.locator("//h2[text()='" + PubName + "']");
        Publisher1.scrollIntoViewIfNeeded();
        System.out.println("Publisher1 : " + Publisher1);


//        page.evaluate("window.scrollBy(0, 900)");
//        page.evaluate(script);

        Locator Pub1_SameJrAck = page.locator("(//h2[text()='" + PubName + "']//following::th[text()='" + J_acrm + "'])[1]");
        Pub1_SameJrAck.scrollIntoViewIfNeeded();
        System.out.println("Publisher1 Journal Acro : " + Pub1_SameJrAck);


        Locator Pub1_SameJrName = page.locator("(//h2[text()='" + PubName + "']//following::th[text()='" + J_acrm + "']//following::div[text()='" + J_name + "'])[1]");
        Pub1_SameJrName.scrollIntoViewIfNeeded();
        System.out.println("Publisher1 Journal name: " + Pub1_SameJrName);

        Locator Publisher2 = page.locator("//h2[text()='" + PubNameII + "']");
        Publisher2.scrollIntoViewIfNeeded();
        System.out.println("Publisher2 :" + PubNameII);

        Locator Pub2_SameJrAck = page.locator("(//h2[text()='" + PubNameII + "']//following::th[text()='" + J_acrm + "'])[1]");
        Pub2_SameJrAck.scrollIntoViewIfNeeded();
        System.out.println("Publisher2 Jour Acro :" + J_acrm);

        Locator Pub2_SameJrName = page.locator("(//h2[text()='" + PubNameII + "']//following::th[text()='" + J_acrm + "']//following::div[text()='" + J_name + "'])[1]");
        Pub2_SameJrName.scrollIntoViewIfNeeded();
        System.out.println("Publisher2 Jour Name :" + J_name);

        if (Publisher1.equals(PubName) && Pub1_SameJrAck.equals(J_acrm) && Pub1_SameJrName.equals(J_name) && Publisher2.equals(PubNameII) && Pub2_SameJrAck.equals(J_acrm) && Pub2_SameJrName.equals(J_acrm)) {
            return true;
        } else {
            return false;
        }

    }

    public List<String> samePubAcro(String Pub, String J_acrm, String J_name, String PubName) {


        page.locator(publisher_1).click();
        page.locator("//div[@class='_options-container_15e5e_189']//following::ul/li//p[text()='" + Pub + "']").click();
        page.locator(jor_acrm).fill(J_acrm);
        page.locator(jor_fullname).fill(J_name);

        generalAddJournals();

        Locator Addpage = page.locator(addButton);
        boolean isVisible = Addpage.isVisible();
        System.out.println("is Add button visible? :" + isVisible);

        Addpage.scrollIntoViewIfNeeded();
        page.waitForSelector("//button[@type='button']");
        Addpage.click();


        page.locator(alertCloseButton).click();
        page.locator(manageMenu).click();
        page.locator(JourView_1).click();
        page.locator(JourView_2).click();

        List<String> publisherInfo = new ArrayList<>();

        String Pub_Title = page.locator("//h2[text()='" + PubName + "']").textContent();
        publisherInfo.add("Publisher Title: " + Pub_Title);
        System.out.println("Publisher title in samePubAcro: " + Pub_Title);


        Locator editJournalOption =
                page.locator("//h2[text()='" + PubName + "']//following::th[text()='" + J_acrm + "']" +
                        "//following::div[text()='" + J_name + "']//following::span[@data-target='#dropright'][1]");

        editJournalOption.scrollIntoViewIfNeeded();
        editJournalOption.click();

        page.waitForSelector(EditJournal).click();

        Locator Pub_Acro = page.locator(publisher_1);
        Pub_Acro.scrollIntoViewIfNeeded();

        String Pub_Acro1 = page.waitForSelector(publisher_1).textContent();
        publisherInfo.add("Publisher Final Acro here: " + Pub_Acro1);
        System.out.println("Print the publisher Acro :" + Pub_Acro);

        return publisherInfo;

    }

    public List<Boolean> CheckRecto(String PubAck, String PubName, String JAcrm, String J_Name) {

        page.locator(publisher_1).click();
        page.locator("//div[@class='_options-container_15e5e_189']//following::ul/li//p[text()='" + PubAck + "']").click();
        page.locator(jor_acrm).fill(JAcrm);
        page.locator(jor_fullname).fill(J_Name);
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        page.locator(jor_received_date).fill(formattedDate);

        List<Boolean> rectoCheckBoxCheck = new ArrayList<>();

        Locator rectoFacingBox = page.locator(recto_facing);

        boolean isClickable = rectoFacingBox.isEnabled();
        rectoCheckBoxCheck.add(isClickable);

        page.locator(recto_facing).click();
        page.locator(layout_1).click();
        page.locator(layout_2).click();
        page.locator(trimSizeWidth).fill("1");
        page.locator(trimSizeHeight).fill("1");
        page.locator(remarks).fill("1");
        page.locator(category_1).click();
        page.locator(category_2_technicalEdit).click();
        page.locator(category_2_typeSetting).click();
        page.locator(category_3_CopyEdit).click();
        page.locator(copyEditLevel_1).click();
        page.locator(copyEditLevel_2).click();
        page.locator(pubType_1).click();
        page.locator(pubType_CheckAll).click();

        page.locator(G_FPdaysOfLatex).fill("2");
        page.locator(G_FPdaysOfGraphics).fill("2");
        page.locator(G_FPdaysOfPreEdit).fill("2");
        page.locator(G_FPdaysOfCopyEdit).fill("2");
        page.locator(G_FPdaysOfTypeSetting).fill("2");
        page.locator(G_FPdaysOfQC).fill("2");

        page.locator(FS_FPdaysOfLatex).fill("3");
        page.locator(FS_FPdaysOfGraphics).fill("3");
        page.locator(FS_FPdaysOfPreEdit).fill("3");
        page.locator(FS_FPdaysOfCopyEdit).fill("3");
        page.locator(FS_FPdaysOfTypeSetting).fill("3");
        page.locator(FS_FPdaysOfQC).fill("3");
        page.evaluate("window.scrollBy(0, 320)");
        page.locator(G_AUdaysOfPage).fill("3");
        page.locator(G_AUdaysOfQC).fill("3");
        page.locator(FS_AUdaysOfPage).fill("3");
        page.locator(FS_AUdaysOfQC).fill("3");


        page.locator(G_PEdaysOfPage).fill("4");
        page.locator(G_PEdaysOfQC).fill("4");
        page.locator(FS_PEdaysOfPage).fill("4");
        page.locator(FS_PEdaysOfQC).fill("4");
        page.evaluate("window.scrollBy(0, 320)");

        page.locator(G_ONFdaysOfPage).fill("5");
        page.locator(G_ONFdaysOfQC).fill("5");
        page.locator(G_ONFdaysOfXML).fill("5");
        page.locator(FS_ONFdaysOfPage).fill("5");
        page.locator(FS_ONFdaysOfQC).fill("5");
        page.locator(FS_ONFdaysOfXML).fill("5");


        page.locator(G_ISSdaysOfPage).fill("6");
        page.locator(G_ISSdaysOfQC).fill("6");
        page.locator(FS_ISSdaysOfPage).fill("6");
        page.locator(FS_ISSdaysOfQC).fill("6");

        page.locator(G_PrintdaysOfPage).fill("7");
        page.locator(G_PrintdaysOfQC).fill("7");
        page.locator(G_PrintdaysOfXML).fill("7");
        page.locator(FS_PrintdaysOfPage).fill("7");
        page.locator(FS_PrintdaysOfQC).fill("7");
        page.locator(FS_PrintdaysOfXML).fill("7");

        fileChooser = page.waitForFileChooser(() -> page.locator(styleTemplateUploadButton).click());
        fileChooser.setFiles(Paths.get("ems_journal.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(guideLineUploadButton).click());
        fileChooser.setFiles(Paths.get("EMS_Press.pdf"));

        page.locator(addButton).click();
        page.locator(manageMenu).click();
        page.locator(JourView_1).click();
        page.locator(JourView_2).click();

        page.evaluate("window.scrollBy(0, 520)");
        Locator rectoIsClicked = page.locator("(//h2[text()='" + PubName + "']//following::th[text()='" + JAcrm + "']" +
                "//following::td//input[@id='recto'])[1]");

        boolean IsRectoCheck = rectoIsClicked.isChecked();
        rectoCheckBoxCheck.add(IsRectoCheck);

        return rectoCheckBoxCheck;


    }

    public List<String> CopyEditingLevel() {

        page.locator(category_1).click();
        page.locator(category_3_CopyEdit).click();
        page.locator(copyEditLevel_1).click();

        List<String> CEDropDown = new ArrayList<>();

        List<String> CE_levels = page.locator("//div[@class='_options-container_15e5e_189']/ul/li").allInnerTexts();
        System.out.println("CopyEditing Levels are: " + CE_levels);


        for (String a : CE_levels) {
            CEDropDown.add(a);
            System.out.println(a);
        }
        return CEDropDown;


    }

    public boolean TrimSizeIsNumeric() {

        String widthType = page.getAttribute(trimSizeWidth, "type");
        String heightType = page.getAttribute(trimSizeHeight, "type");

        return "number".equalsIgnoreCase(widthType) && "number".equalsIgnoreCase(heightType);


    }

    public List<String> GenToFastCopyTat() {

        page.locator(G_FPdaysOfLatex).fill("1");
        page.locator(G_FPdaysOfGraphics).fill("2");
        page.locator(G_FPdaysOfPreEdit).fill("3");
        page.locator(G_FPdaysOfCopyEdit).fill("4");
        page.locator(G_FPdaysOfTypeSetting).fill("5");
        page.locator(G_FPdaysOfQC).fill("6");

        page.locator(G_AUdaysOfPage).fill("7");
        page.locator(G_AUdaysOfQC).fill("8");

        page.locator(G_PEdaysOfPage).fill("9");
        page.locator(G_PEdaysOfQC).fill("1");

        page.locator(G_ONFdaysOfPage).fill("2");
        page.locator(G_ONFdaysOfQC).fill("3");
        page.locator(G_ONFdaysOfXML).fill("4");

        page.locator(G_ISSdaysOfPage).fill("5");
        page.locator(G_ISSdaysOfQC).fill("6");

        page.locator(G_PrintdaysOfPage).fill("7");
        page.locator(G_PrintdaysOfQC).fill("8");
        page.locator(G_PrintdaysOfXML).fill("9");

        page.waitForSelector(general_to_fast).click();
        page.waitForSelector(importCopyTAT).click();


        List<String> FastValues = new ArrayList<>();


        FastValues.add(page.locator(FS_FPdaysOfLatex).inputValue());
        FastValues.add(page.locator(FS_FPdaysOfGraphics).inputValue());
        FastValues.add(page.locator(FS_FPdaysOfPreEdit).inputValue());
        FastValues.add(page.locator(FS_FPdaysOfCopyEdit).inputValue());
        FastValues.add(page.locator(FS_FPdaysOfTypeSetting).inputValue());
        FastValues.add(page.locator(FS_FPdaysOfQC).inputValue());

        FastValues.add(page.locator(FS_AUdaysOfPage).inputValue());
        FastValues.add(page.locator(FS_AUdaysOfQC).inputValue());

        FastValues.add(page.locator(FS_PEdaysOfPage).inputValue());
        FastValues.add(page.locator(FS_PEdaysOfQC).inputValue());

        FastValues.add(page.locator(FS_ONFdaysOfPage).inputValue());
        FastValues.add(page.locator(FS_ONFdaysOfQC).inputValue());
        FastValues.add(page.locator(FS_ONFdaysOfXML).inputValue());

        FastValues.add(page.locator(FS_ISSdaysOfPage).inputValue());
        FastValues.add(page.locator(FS_ISSdaysOfQC).inputValue());

        FastValues.add(page.locator(FS_PrintdaysOfPage).inputValue());
        FastValues.add(page.locator(FS_PrintdaysOfQC).inputValue());
        FastValues.add(page.locator(FS_PrintdaysOfXML).inputValue());

        return FastValues;


    }

    public List<String> FastToGenCopyTat() {


        page.locator(FS_FPdaysOfLatex).fill("1");
        page.locator(FS_FPdaysOfGraphics).fill("2");
        page.locator(FS_FPdaysOfPreEdit).fill("3");
        page.locator(FS_FPdaysOfCopyEdit).fill("4");
        page.locator(FS_FPdaysOfTypeSetting).fill("5");
        page.locator(FS_FPdaysOfQC).fill("6");

        page.locator(FS_AUdaysOfPage).fill("7");
        page.locator(FS_AUdaysOfQC).fill("8");

        page.locator(FS_PEdaysOfPage).fill("9");
        page.locator(FS_PEdaysOfQC).fill("1");

        page.locator(FS_ONFdaysOfPage).fill("2");
        page.locator(FS_ONFdaysOfQC).fill("3");
        page.locator(FS_ONFdaysOfXML).fill("4");

        page.locator(FS_ISSdaysOfPage).fill("5");
        page.locator(FS_ISSdaysOfQC).fill("6");

        page.locator(FS_PrintdaysOfPage).fill("7");
        page.locator(FS_PrintdaysOfQC).fill("8");
        page.locator(FS_PrintdaysOfXML).fill("9");

        page.waitForSelector(fast_to_general).click();
        page.waitForSelector(importCopyTAT).click();

        List<String> GeneralValues = new ArrayList<>();

        GeneralValues.add(page.locator(G_FPdaysOfLatex).inputValue());
        GeneralValues.add(page.locator(G_FPdaysOfGraphics).inputValue());
        GeneralValues.add(page.locator(G_FPdaysOfPreEdit).inputValue());
        GeneralValues.add(page.locator(G_FPdaysOfCopyEdit).inputValue());
        GeneralValues.add(page.locator(G_FPdaysOfTypeSetting).inputValue());
        GeneralValues.add(page.locator(G_FPdaysOfQC).inputValue());

        GeneralValues.add(page.locator(G_AUdaysOfPage).inputValue());
        GeneralValues.add(page.locator(G_AUdaysOfQC).inputValue());

        GeneralValues.add(page.locator(G_PEdaysOfPage).inputValue());
        GeneralValues.add(page.locator(G_PEdaysOfQC).inputValue());

        GeneralValues.add(page.locator(G_ONFdaysOfPage).inputValue());
        GeneralValues.add(page.locator(G_ONFdaysOfQC).inputValue());
        GeneralValues.add(page.locator(G_ONFdaysOfXML).inputValue());

        GeneralValues.add(page.locator(G_ISSdaysOfPage).inputValue());
        GeneralValues.add(page.locator(G_ISSdaysOfQC).inputValue());

        GeneralValues.add(page.locator(G_PrintdaysOfPage).inputValue());
        GeneralValues.add(page.locator(G_PrintdaysOfQC).inputValue());
        GeneralValues.add(page.locator(G_PrintdaysOfXML).inputValue());


        return GeneralValues;
    }

    public List<String> TATModificationGeneralToFastTrack(String Pub, String J_acrm, String J_Name, String PubName) {

        page.locator(publisher_1).click();
        page.locator("//p[normalize-space()='"+Pub+"']").click();
        page.locator(jor_acrm).fill(J_acrm);
        page.locator(jor_fullname).fill(J_Name);
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        page.locator(jor_received_date).fill(formattedDate);
        page.locator(recto_facing).click();
        page.locator(layout_1).click();
        page.locator(layout_2).click();
        page.locator(trimSizeWidth).fill("2");
        page.locator(trimSizeHeight).fill("3");
        page.locator(remarks).fill("Test remarks");
        page.locator(category_1).click();
        page.locator(category_2_technicalEdit).click();
        page.locator(category_2_typeSetting).click();
        page.locator(category_3_CopyEdit).click();
        page.locator(copyEditLevel_1).click();
        page.locator(copyEditLevel_2).click();
        page.locator(pubType_1).click();
        page.locator(pubType_CheckAll).click();

        GenToFastCopyTat();

        //Edit the imported Fasttrack values

        page.locator(FS_FPdaysOfLatex).fill("1");
        page.locator(FS_FPdaysOfGraphics).fill("1");
        page.locator(FS_FPdaysOfPreEdit).fill("1");
        page.locator(FS_FPdaysOfCopyEdit).fill("1");
        page.locator(FS_FPdaysOfTypeSetting).fill("1");
        page.locator(FS_FPdaysOfQC).fill("1");

        page.locator(FS_AUdaysOfPage).fill("1");
        page.locator(FS_AUdaysOfQC).fill("1");

        page.locator(FS_PEdaysOfPage).fill("1");
        page.locator(FS_PEdaysOfQC).fill("1");

        page.locator(FS_ONFdaysOfPage).fill("1");
        page.locator(FS_ONFdaysOfQC).fill("1");
        page.locator(FS_ONFdaysOfXML).fill("1");

        page.locator(FS_ISSdaysOfPage).fill("1");
        page.locator(FS_ISSdaysOfQC).fill("1");

        page.locator(FS_PrintdaysOfPage).fill("1");
        page.locator(FS_PrintdaysOfQC).fill("1");
        page.locator(FS_PrintdaysOfXML).fill("1");

        fileChooser = page.waitForFileChooser(() -> page.locator(styleTemplateUploadButton).click());
        fileChooser.setFiles(Paths.get("ems_journal.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(guideLineUploadButton).click());
        fileChooser.setFiles(Paths.get("EMS_Press.pdf"));


        page.locator(addButton).click();
        page.locator(alertCloseButton).click();

        Locator editJournalOption =
                page.locator("//h2[text()='"+PubName+"']//following::th[text()='"+J_acrm+"']//following::div[text()='"+J_Name+"']//following::span[@data-target='#dropright'][1]");

        editJournalOption.scrollIntoViewIfNeeded();
        editJournalOption.click();
        page.waitForSelector(EditJournal).click();

        List<String> EditedFastValues = new ArrayList<>();

        EditedFastValues.add(page.locator(FS_FPdaysOfLatex).inputValue());
        EditedFastValues.add(page.locator(FS_FPdaysOfGraphics).inputValue());
        EditedFastValues.add(page.locator(FS_FPdaysOfPreEdit).inputValue());
        EditedFastValues.add(page.locator(FS_FPdaysOfCopyEdit).inputValue());
        EditedFastValues.add(page.locator(FS_FPdaysOfTypeSetting).inputValue());
        EditedFastValues.add(page.locator(FS_FPdaysOfQC).inputValue());

        EditedFastValues.add(page.locator(FS_AUdaysOfPage).inputValue());
        EditedFastValues.add(page.locator(FS_AUdaysOfQC).inputValue());

        EditedFastValues.add(page.locator(FS_PEdaysOfPage).inputValue());
        EditedFastValues.add(page.locator(FS_PEdaysOfQC).inputValue());

        EditedFastValues.add(page.locator(FS_ONFdaysOfPage).inputValue());
        EditedFastValues.add(page.locator(FS_ONFdaysOfQC).inputValue());
        EditedFastValues.add(page.locator(FS_ONFdaysOfXML).inputValue());

        EditedFastValues.add(page.locator(FS_ISSdaysOfPage).inputValue());
        EditedFastValues.add(page.locator(FS_ISSdaysOfQC).inputValue());

        EditedFastValues.add(page.locator(FS_PrintdaysOfPage).inputValue());
        EditedFastValues.add(page.locator(FS_PrintdaysOfQC).inputValue());
        EditedFastValues.add(page.locator(FS_PrintdaysOfXML).inputValue());

        return EditedFastValues;


    }

    public List<String> TATModificationFastTrackToGeneral(String Pub, String J_acrm, String J_Name, String PubName) {

        page.locator(publisher_1).click();
        page.locator("//p[normalize-space()='"+Pub+"']").click();
        page.locator(jor_acrm).fill("ODS");
        page.locator(jor_fullname).fill("Otto");
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        page.locator(jor_received_date).fill(formattedDate);
        page.locator(recto_facing).click();
        page.locator(layout_1).click();
        page.locator(layout_2).click();
        page.locator(trimSizeWidth).fill("2");
        page.locator(trimSizeHeight).fill("3");
        page.locator(remarks).fill("Test remarks");
        page.locator(category_1).click();
        page.locator(category_2_technicalEdit).click();
        page.locator(category_2_typeSetting).click();
        page.locator(category_3_CopyEdit).click();
        page.locator(copyEditLevel_1).click();
        page.locator(copyEditLevel_2).click();
        page.locator(pubType_1).click();
        page.locator(pubType_CheckAll).click();

        FastToGenCopyTat();

        page.locator(G_FPdaysOfLatex).fill("2");
        page.locator(G_FPdaysOfGraphics).fill("2");
        page.locator(G_FPdaysOfPreEdit).fill("2");
        page.locator(G_FPdaysOfCopyEdit).fill("2");
        page.locator(G_FPdaysOfTypeSetting).fill("2");
        page.locator(G_FPdaysOfQC).fill("2");

        page.locator(G_AUdaysOfPage).fill("2");
        page.locator(G_AUdaysOfQC).fill("2");

        page.locator(G_PEdaysOfPage).fill("2");
        page.locator(G_PEdaysOfQC).fill("2");

        page.locator(G_ONFdaysOfPage).fill("2");
        page.locator(G_ONFdaysOfQC).fill("2");
        page.locator(G_ONFdaysOfXML).fill("2");

        page.locator(G_ISSdaysOfPage).fill("2");
        page.locator(G_ISSdaysOfQC).fill("2");

        page.locator(G_PrintdaysOfPage).fill("3");
        page.locator(G_PrintdaysOfQC).fill("3");
        page.locator(G_PrintdaysOfXML).fill("3");

        fileChooser = page.waitForFileChooser(() -> page.locator(styleTemplateUploadButton).click());
        fileChooser.setFiles(Paths.get("ems_journal.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(guideLineUploadButton).click());
        fileChooser.setFiles(Paths.get("EMS_Press.pdf"));

        page.locator(addButton).click();
        page.locator(alertCloseButton).click();

        Locator editJournalOption =
                page.locator("//h2[text()='"+PubName+"']//following::th[text()='"+J_acrm+"']//following::div[text()='"+J_Name+"']//following::span[@data-target='#dropright'][1]");

        editJournalOption.scrollIntoViewIfNeeded();
        editJournalOption.click();
        page.waitForSelector(EditJournal).click();

        List<String> EditedGeneralValues = new ArrayList<>();

        EditedGeneralValues.add(page.locator(G_FPdaysOfLatex).inputValue());
        EditedGeneralValues.add(page.locator(G_FPdaysOfGraphics).inputValue());
        EditedGeneralValues.add(page.locator(G_FPdaysOfPreEdit).inputValue());
        EditedGeneralValues.add(page.locator(G_FPdaysOfCopyEdit).inputValue());
        EditedGeneralValues.add(page.locator(G_FPdaysOfTypeSetting).inputValue());
        EditedGeneralValues.add(page.locator(G_FPdaysOfQC).inputValue());

        EditedGeneralValues.add(page.locator(G_AUdaysOfPage).inputValue());
        EditedGeneralValues.add(page.locator(G_AUdaysOfQC).inputValue());

        EditedGeneralValues.add(page.locator(G_PEdaysOfPage).inputValue());
        EditedGeneralValues.add(page.locator(G_PEdaysOfQC).inputValue());

        EditedGeneralValues.add(page.locator(G_ONFdaysOfPage).inputValue());
        EditedGeneralValues.add(page.locator(G_ONFdaysOfQC).inputValue());
        EditedGeneralValues.add(page.locator(G_ONFdaysOfXML).inputValue());

        EditedGeneralValues.add(page.locator(G_ISSdaysOfPage).inputValue());
        EditedGeneralValues.add(page.locator(G_ISSdaysOfQC).inputValue());

        EditedGeneralValues.add(page.locator(G_PrintdaysOfPage).inputValue());
        EditedGeneralValues.add(page.locator(G_PrintdaysOfQC).inputValue());
        EditedGeneralValues.add(page.locator(G_PrintdaysOfXML).inputValue());

        return EditedGeneralValues;


    }

    public List<String> TATImportFromGenToFast(String Pub, String J_acrm, String J_name,
                                               String PubName, String J_AcroE, String J_NameE) {

        generalPubAcroWithJname(Pub, J_acrm, J_name);
        GenToFastCopyTat();

        fileChooser = page.waitForFileChooser(() -> page.locator(styleTemplateUploadButton).click());
        fileChooser.setFiles(Paths.get("ems_journal.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(guideLineUploadButton).click());
        fileChooser.setFiles(Paths.get("EMS_Press.pdf"));

        page.locator(addButton).click();
        page.locator(alertCloseButton).click();

        editJournals(PubName, J_acrm, J_name);

        List<String> FastValues = new ArrayList<>();

        FastValues.add(page.locator(FS_FPdaysOfLatex).inputValue());
        FastValues.add(page.locator(FS_FPdaysOfGraphics).inputValue());
        FastValues.add(page.locator(FS_FPdaysOfPreEdit).inputValue());
        FastValues.add(page.locator(FS_FPdaysOfCopyEdit).inputValue());
        FastValues.add(page.locator(FS_FPdaysOfTypeSetting).inputValue());
        FastValues.add(page.locator(FS_FPdaysOfQC).inputValue());

        FastValues.add(page.locator(FS_AUdaysOfPage).inputValue());
        FastValues.add(page.locator(FS_AUdaysOfQC).inputValue());

        FastValues.add(page.locator(FS_PEdaysOfPage).inputValue());
        FastValues.add(page.locator(FS_PEdaysOfQC).inputValue());

        FastValues.add(page.locator(FS_ONFdaysOfPage).inputValue());
        FastValues.add(page.locator(FS_ONFdaysOfQC).inputValue());
        FastValues.add(page.locator(FS_ONFdaysOfXML).inputValue());

        FastValues.add(page.locator(FS_ISSdaysOfPage).inputValue());
        FastValues.add(page.locator(FS_ISSdaysOfQC).inputValue());

        FastValues.add(page.locator(FS_PrintdaysOfPage).inputValue());
        FastValues.add(page.locator(FS_PrintdaysOfQC).inputValue());
        FastValues.add(page.locator(FS_PrintdaysOfXML).inputValue());

        return FastValues;


    }


    public List<String> TATImportFromFastToGen(String Pub, String J_acrm, String J_name,
                                               String PubName, String J_AcroE, String J_NameE) {


        generalPubAcroWithJname(Pub, J_acrm, J_name);
        FastToGenCopyTat();

        fileChooser = page.waitForFileChooser(() -> page.locator(styleTemplateUploadButton).click());
        fileChooser.setFiles(Paths.get("ems_journal.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(guideLineUploadButton).click());
        fileChooser.setFiles(Paths.get("EMS_Press.pdf"));

        page.locator(addButton).click();
        page.locator(alertCloseButton).click();

        editJournals(PubName, J_acrm, J_name);

        List<String> GeneralValues = new ArrayList<>();

        GeneralValues.add(page.locator(G_FPdaysOfLatex).inputValue());
        GeneralValues.add(page.locator(G_FPdaysOfGraphics).inputValue());
        GeneralValues.add(page.locator(G_FPdaysOfPreEdit).inputValue());
        GeneralValues.add(page.locator(G_FPdaysOfCopyEdit).inputValue());
        GeneralValues.add(page.locator(G_FPdaysOfTypeSetting).inputValue());
        GeneralValues.add(page.locator(G_FPdaysOfQC).inputValue());

        GeneralValues.add(page.locator(G_AUdaysOfPage).inputValue());
        GeneralValues.add(page.locator(G_AUdaysOfQC).inputValue());

        GeneralValues.add(page.locator(G_PEdaysOfPage).inputValue());
        GeneralValues.add(page.locator(G_PEdaysOfQC).inputValue());

        GeneralValues.add(page.locator(G_ONFdaysOfPage).inputValue());
        GeneralValues.add(page.locator(G_ONFdaysOfQC).inputValue());
        GeneralValues.add(page.locator(G_ONFdaysOfXML).inputValue());

        GeneralValues.add(page.locator(G_ISSdaysOfPage).inputValue());
        GeneralValues.add(page.locator(G_ISSdaysOfQC).inputValue());

        GeneralValues.add(page.locator(G_PrintdaysOfPage).inputValue());
        GeneralValues.add(page.locator(G_PrintdaysOfQC).inputValue());
        GeneralValues.add(page.locator(G_PrintdaysOfXML).inputValue());


        return GeneralValues;


    }


    public String moveLatestToArchiveAndVerify(String Pub, String J_acrm, String J_name, String PubName, String Arch_Sty) {

        System.out.println("---------");

        System.out.println(Pub);
        System.out.println(J_acrm);
        System.out.println(J_name);
        System.out.println(PubName);
        System.out.println(Arch_Sty);
        System.out.println("---------");

        generalPubAcroWithJname(Pub, J_acrm, J_name);
        GenToFastCopyTat();
        FastToGenCopyTat();

        fileChooser = page.waitForFileChooser(() -> page.locator(styleTemplateUploadButton).click());
        fileChooser.setFiles(Paths.get("ems_journal.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(guideLineUploadButton).click());
        fileChooser.setFiles(Paths.get("EMS_Press.pdf"));


        page.locator(addButton).click();
        page.locator(alertCloseButton).click();

        editJournals(PubName, J_acrm, J_name);

        page.locator("//div[@class='_style-upload-list_hx1h0_669']" +
                "//following::p[@title='" + Arch_Sty + "']//following::img[@title='move to archieve'][1]").click();


        page.locator(LatestToArch_Yes).click();


        String Archive_Styletext = page.locator("//div[@class='_style-upload-list_hx1h0_669']//following::p[@title='" + Arch_Sty + "']").textContent();


        return Archive_Styletext;


    }

    public Boolean JrCreationByChangingPub(String Pub, String J_acrm, String J_name, String PubNewAckro, String PubNameNew2) {


        System.out.println("---------");

        System.out.println(Pub);
        System.out.println(J_acrm);
        System.out.println(J_name);
        System.out.println(PubNewAckro);
        System.out.println(PubNameNew2);
        System.out.println("---------");


        generalPubAcroWithJname(Pub, J_acrm, J_name);
        GenToFastCopyTat();
        FastToGenCopyTat();

        fileChooser = page.waitForFileChooser(() -> page.locator(styleTemplateUploadButton).click());
        fileChooser.setFiles(Paths.get("ems_journal.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(guideLineUploadButton).click());
        fileChooser.setFiles(Paths.get("EMS_Press.pdf"));

        page.locator(publisher_1).click();
        page.locator("//div[@class='_options-container_15e5e_189']//following::ul/li//p[text()='" + PubNewAckro + "']").click();

        page.locator(addButton).click();
        page.locator(alertCloseButton).click();

        Locator JournalNew =
                page.locator("//h2[text()='" + PubNameNew2 + "']//following::th[text()='" + J_acrm + "']//following::div[text()='" + J_name + "']");

        page.waitForSelector("//h2[text()='" + PubNameNew2 + "']//following::th[text()='" + J_acrm + "']//following::div[text()='" + J_name + "']");


        JournalNew.scrollIntoViewIfNeeded();

        System.out.println("Publisher2 Jour Name :" + J_name);

        if (JournalNew.equals(J_name)) {

            return true;
        } else {
            return false;
        }


    }

    public void addPublisher(String acro, String pub, String c, String d, String e, String f, String g, String h) {


        page.locator(baseicon).click();
        page.locator(addpubicon).click();
        page.locator(pub_acronym).fill(acro);
        page.locator(pub_name_textbox).fill(pub);
        // Assert.assertEquals(page.locator(pub_acronym).inputValue(), a, "pub_acronym not filled correctly");
        page.locator(pub_mail_textbox).fill(c);
        page.locator(desc_inputbox).fill(d);
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        page.locator(selectdateinput).fill(formattedDate);
        page.locator(selectdateinput).fill(formattedDate);
        page.locator(pub_location).fill(e);
        page.locator(ftpusername).fill(f);
        page.locator(ftppassword).fill(g);
        page.locator(ftp_initial_directory).fill(h);
        GenToFastCopyTat();
        FastToGenCopyTat();

        fileChooser = page.waitForFileChooser(() -> page.locator(Imageuploadbutton).click());
        fileChooser.setFiles(Paths.get("Image.jpg"));

        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ems_journal.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("GuidelinesNew.docx"));

        page.locator(addbutton).click();
        page.locator(addalertclose).click();


    }

    public boolean createJrWithoutImportFiles(String Pub, String J_acrm, String J_name) {

        generalPubAcroWithJname(Pub, J_acrm, J_name);
        GenToFastCopyTat();
        FastToGenCopyTat();
        page.locator(addButton).click();

        Locator ErrMsg = page.locator("//div[@class='_modalContent_1rfj1_12']//following::div[text()='Before submit, please upload Latest Style Template.']");
        return ErrMsg.isVisible();


    }

    public boolean checkJournalAdded(String Pub, String J_acrm, String J_name, String PubName) {

        generalPubAcroWithJname(Pub, J_acrm, J_name);
        GenToFastCopyTat();
        FastToGenCopyTat();

        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ems_journal.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("GuidelinesNew.docx"));

        page.locator(addButton).click();

        page.locator(manageMenu).click();
        page.locator(JourView_1).click();
        page.locator(JourView_2).click();
        page.waitForTimeout(1000);


        Locator Publisher = page.locator("//h2[text()='" + PubName + "']");
        Publisher.scrollIntoViewIfNeeded();
        System.out.println("PublisherName : " + Publisher);

        Locator Pub_SameJrAck = page.locator("(//h2[text()='" + PubName + "']//following::th[text()='" + J_acrm + "'])[1]");
        Pub_SameJrAck.scrollIntoViewIfNeeded();
        System.out.println("Publisher Journal Acro : " + Pub_SameJrAck);

        Locator Pub_SameJrName = page.locator("(//h2[text()='" + PubName + "']//following::th[text()='" + J_acrm + "']//following::div[text()='" + J_name + "'])[1]");
        Pub_SameJrName.scrollIntoViewIfNeeded();
        System.out.println("Publisher Journal name: " + Pub_SameJrName);


        if (Publisher.equals(PubName) && Pub_SameJrAck.equals(J_acrm) && Pub_SameJrName.equals(J_name)) {
            return true;
        } else {
            return false;
        }


    }


    public List<Boolean> JournalCantDuplicated(String Pub, String J_acrm, String J_name, String PubName) {


        List<Boolean> JournalDup = new ArrayList<>();
        try {
            page.locator(baseicon).click();
            page.locator(addjouricon).click();
            page.locator(publisher_1).click();
            page.locator("//div[@class='_options-container_15e5e_189']//following::ul/li//p[text()='" + Pub + "']").click();
            page.locator(jor_acrm).fill(J_acrm);
            page.locator(jor_fullname).fill(J_name);
            generalAddJournals();

            page.locator(addButton).click();
            page.locator(alertCloseButton).click();

            page.locator(manageMenu).click();
            page.locator(JourView_1).click();
            page.locator(JourView_2).click();
            page.waitForTimeout(1000);

//        Locator PublisherName = page.locator("//h2[text()='" + PubName + "']");
//        PublisherName.scrollIntoViewIfNeeded();
//        boolean actualPublisherName = PublisherName.isVisible();
//        System.out.println("Publisher1 Name: " + actualPublisherName);

//        Locator Pub_SameJrAck = page.locator("(//h2[text()='" + PubName + "']//following::th[text()='" + J_acrm + "'])[1]");
//        Pub_SameJrAck.scrollIntoViewIfNeeded();
//        boolean actualPub_SameJrAck = Pub_SameJrAck.isVisible();
//        System.out.println("Publisher1 Journal Acro : " + actualPub_SameJrAck);

            page.waitForTimeout(1000);

            String journalElementXPath = "(//h2[text()='" + PubName + "']//following::th[text()='" + J_acrm + "']//following::div[text()='" + J_name + "'])[1]";
            Locator Pub_SameJrName = page.locator(journalElementXPath);
            Pub_SameJrName.scrollIntoViewIfNeeded();

            boolean actualPub_SameJrName = Pub_SameJrName.isVisible();
            System.out.println("Publisher Journal name: " + actualPub_SameJrName);
            JournalDup.add(actualPub_SameJrName);

        } catch (Exception e) {
            System.out.println("Error while checking journal duplication: " + e.getMessage());
            JournalDup.add(false);
            // Optionally handle or log the exception
        }

        return JournalDup;
    }

    public List<Boolean> ImportIconClickWithoutFiles(String Pub, String J_acrm, String J_name) {

        generalPubAcroWithJname(Pub, J_acrm, J_name);
        GenToFastCopyTat();
        FastToGenCopyTat();

        List<Boolean> ImportFileCheck = new ArrayList<>();
        page.waitForTimeout(1000);

        Locator sty_latest = page.locator("//div[@class='_style-editor-container_132u2_451']//div[@class='_style-upload-list_132u2_662']");
        boolean Style_latestFiles = sty_latest.isVisible();
        System.out.println("Visibility of Latest Files Sty before import: " + Style_latestFiles);
        ImportFileCheck.add(Style_latestFiles);

        page.locator(importStyleTemplate).click();
        page.locator(importFileTATyes).click();

        //after import clicked for style template
        Locator sty_latest_1 = page.locator("//div[@class='_style-editor-container_132u2_451']//div[@class='_style-upload-list_132u2_662']");
        boolean Style_latestFiles_1 = sty_latest_1.isVisible();
        System.out.println("Visibility of Latest Files Sty after import: " + Style_latestFiles);
        ImportFileCheck.add(Style_latestFiles_1);


        Locator guide_doc = page.locator("//div[@class='_upload-and-list-container_132u2_601']//div[@class='_guidelines-upload-list_132u2_704']");
        boolean Guideline_document = guide_doc.isVisible();
        System.out.println("Visibility of Latest Files Guidelines before import: " + Guideline_document);
        ImportFileCheck.add(Guideline_document);

        page.locator(importGuideLinesDocument).click();
        page.locator(importFileTATyes).click();

        //after import clicked for guideline template
        Locator guide_doc_1 = page.locator("//div[@class='_upload-and-list-container_132u2_601']//div[@class='_guidelines-upload-list_132u2_704']");
        boolean Guideline_document_1 = guide_doc_1.isVisible();
        System.out.println("Visibility of Latest Files Guidelines after import: " + Guideline_document_1);
        ImportFileCheck.add(Guideline_document_1);


        return ImportFileCheck;


    }


    public List<String> genTATverifyByAddArticle(String artname) {

        page.locator(manageMenu_Art).click();
        page.locator(ArtView_1).click();
        page.locator(ArtView_2).click();
        page.locator("//th[normalize-space()='" + artname + "']//following::td[@class='_edit_icon_td_6185f_96'][1]").click();
        page.locator(TATShow).click();
        page.locator(trackType).click();
        page.locator(GenTrack).click();
        page.locator(importtatfromjournal).click();
        page.locator(confirmimportfromjour).click();


        List<String> GeneralValues = new ArrayList<>();
        GeneralValues.add(page.locator(daysforlatexnormalization).inputValue());
        GeneralValues.add(page.locator(daysforgraphics).inputValue());
        GeneralValues.add(page.locator(daysforPreediting).inputValue());
        GeneralValues.add(page.locator(daysforcopyediting).inputValue());
        GeneralValues.add(page.locator(daysfortypesettings).inputValue());
        GeneralValues.add(page.locator(daysforqc).inputValue());

        GeneralValues.add(page.locator(daysforaupag).inputValue());
        GeneralValues.add(page.locator(daysforauqc).inputValue());

        GeneralValues.add(page.locator(daysforpepag).inputValue());
        GeneralValues.add(page.locator(daysforpeqc).inputValue());

        GeneralValues.add(page.locator(daysforonlinepag).inputValue());
        GeneralValues.add(page.locator(daysforonlineqc).inputValue());
        GeneralValues.add(page.locator(daysforonlinexml).inputValue());

        GeneralValues.add(page.locator(daysforIssuePag).inputValue());
        GeneralValues.add(page.locator(daysforIssueQC).inputValue());

        GeneralValues.add(page.locator(daysforprintpag).inputValue());
        GeneralValues.add(page.locator(daysforprintQC).inputValue());
        GeneralValues.add(page.locator(daysforprintxml).inputValue());


        return GeneralValues;


    }

    public List<String> fastTATverifyByAddArticle(String artname) {

        page.locator(manageMenu_Art).click();
        page.locator(ArtView_1).click();
        page.locator(ArtView_2).click();
        page.locator("//th[normalize-space()='" + artname + "']//following::td[@class='_edit_icon_td_6185f_96'][1]").click();
        System.out.println("Article Name: " + artname);
        page.locator(TATShow).click();
        page.locator(trackType).click();
        page.locator(FastTrack).click();
        page.locator(importtatfromjournal).click();
        page.locator(confirmimportfromjour).click();

        List<String> FastTrackValues = new ArrayList<>();

        FastTrackValues.add(page.locator(daysforlatexnormalization).inputValue());
        FastTrackValues.add(page.locator(daysforgraphics).inputValue());
        FastTrackValues.add(page.locator(daysforPreediting).inputValue());
        FastTrackValues.add(page.locator(daysforcopyediting).inputValue());
        FastTrackValues.add(page.locator(daysfortypesettings).inputValue());
        FastTrackValues.add(page.locator(daysforqc).inputValue());

        FastTrackValues.add(page.locator(daysforaupag).inputValue());
        FastTrackValues.add(page.locator(daysforauqc).inputValue());

        FastTrackValues.add(page.locator(daysforpepag).inputValue());
        FastTrackValues.add(page.locator(daysforpeqc).inputValue());

        FastTrackValues.add(page.locator(daysforonlinepag).inputValue());
        FastTrackValues.add(page.locator(daysforonlineqc).inputValue());
        FastTrackValues.add(page.locator(daysforonlinexml).inputValue());

        FastTrackValues.add(page.locator(daysforIssuePag).inputValue());
        FastTrackValues.add(page.locator(daysforIssueQC).inputValue());

        FastTrackValues.add(page.locator(daysforprintpag).inputValue());
        FastTrackValues.add(page.locator(daysforprintQC).inputValue());
        FastTrackValues.add(page.locator(daysforprintxml).inputValue());


        return FastTrackValues;


    }


    public void ImportStyFromPub(String Pub, String J_acrm, String J_name) throws InterruptedException {


        generalPubAcroWithJname(Pub, J_acrm, J_name);
        GenToFastCopyTat();

        page.locator(importStyleTemplate).click();
        page.locator(importFileTATyes).click();


        page.waitForTimeout(8000);
        Locator Latest_files = page.locator("//div[@class='_style-upload-list_132u2_662']//following::ul/li[@class='_file-row_132u2_746']");
        boolean checkVs = Latest_files.isVisible();
        System.out.println("Visiblity of list: " + checkVs);

        latestFilesCount = Latest_files.count();

        System.out.println("Number of Latest Files in Style Temp: " + latestFilesCount);


        for (int i = 0; i < latestFilesCount; i++) {
            Locator itemLocator = Latest_files.nth(i);
            String Latesttext = itemLocator.textContent();
            latestFilesTexts.add(Latesttext);
            System.out.println("Text of item " + (i + 1) + ": " + Latesttext);
        }

    }

    public List<String> getFileTexts() {
        return latestFilesTexts;
    }

    public int getFileCount() {
        return latestFilesCount;

    }


    public void ImportGuideFromPub(String Pub, String J_acrm, String J_name) {


        generalPubAcroWithJname(Pub, J_acrm, J_name);
        GenToFastCopyTat();

        page.locator(importGuideLinesDocument).click();
        page.locator(importFileTATyes).click();


        page.waitForTimeout(8000);
        Locator Latest_files = page.locator("//div[@class='_guidelines-upload-list_132u2_704']//following::ul/li[@class='_file-row_132u2_746']");
        boolean checkVs = Latest_files.isVisible();
        System.out.println("Visiblity of list: " + checkVs);

        guideDocFilesCount = Latest_files.count();

        System.out.println("Number of Latest Files in Guideline doc: " + guideDocFilesCount);


        for (int i = 0; i < guideDocFilesCount; i++) {
            Locator itemLocator = Latest_files.nth(i);
            String Latesttext = itemLocator.textContent();
            guideDocFilesTexts.add(Latesttext);
            System.out.println("Text of item " + (i + 1) + ": " + Latesttext);
        }


    }

    public List<String> getGuideFileTexts() {
        return guideDocFilesTexts;
    }

    public int getGuideFileCount() {
        return guideDocFilesCount;
    }

    public String generaladdArticle(String journalacro, String artname, String workflow) throws InterruptedException {

        page.locator(baseicon).click();
        assertThat(page.locator(addarticleicon)).isVisible();
        page.locator(addarticleicon).click();
        page.locator(form).click();

        LocalDate today = LocalDate.now();
        LocalDate tomarrow = today.plusDays(1);
        LocalDate DayOftomarrow = today.plusDays(2);


        String formattedDate = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String tomorrow = (today.plusDays(1)).format(DateTimeFormatter.ISO_DATE);
        String dayoftomorrow = (today.plusDays(2)).format(DateTimeFormatter.ISO_DATE);


        page.locator(clickSelectPub).click();

        page.locator(Selectpubdropdown).fill(journalacro);
        page.waitForTimeout(1000);
        Locator JAck = page.locator("//p[normalize-space(text())='" + journalacro + "']");
        JAck.scrollIntoViewIfNeeded();
        JAck.click();
        String arttimeid = String.valueOf(System.currentTimeMillis());
        page.locator(articleidinput).fill(arttimeid);

        page.locator(authormail).fill("abc@gmail.com");
        page.locator(authorname).fill("Mahindra");
        page.locator(articlename).fill(artname);
        page.locator(selectpriority).click();
        page.locator(selectpriorityopt).click();
        page.locator(receivedate).fill(formattedDate);
        page.locator(reviseddate).fill(tomorrow);
        page.locator(Accepteddate).fill(dayoftomorrow);
        page.locator(selecttat).click();
        page.locator(selecttatinput).click();

        int doi = 1;
        long doinumber = Long.parseLong(arttimeid);
        long doival = doi + doinumber;
        String doivalue = String.valueOf(doival);

        page.locator(Doino).fill(doivalue);
        page.locator(workflowselection).click();
        page.locator("//*[@alt='" + workflow + "']").click();
        page.locator(assignbutton).click();
        page.locator(noofpages).fill("200");
        page.locator(articletype).fill("Research");
        page.locator(cebypass).click();
        page.locator(TATShow).click();
        page.locator(importtatfromjournal).click();
        page.locator(confirmimportfromjour).click();
        page.locator(ChecklistSelectionShow).click();
        page.locator(startdate).fill(formattedDate);
        page.locator(OnOpenAccess).click();
        fileChooser = page.waitForFileChooser(() -> page.locator(fileupload).click());
        fileChooser.setFiles(Paths.get("Sample.zip"));

        page.locator(addnotes).click();

        page.locator(Plzwwritehere).fill("this particular article is from general workflow");
        page.locator(AddNoteutton).click();
        page.locator(addnotetoastclose).click();

        page.locator(mailpreview).click();
        page.locator(ccmail).click();
        page.locator(checkall).click();
        page.locator(tomail).click();
        page.locator(checkall).click();
        page.locator(Acknowlegeemtnsavemailbutton).click();
        page.locator(Acknowledgementyesalert).click();
        page.locator(Acknowlegementtoastclose).click();
        page.locator(notificationmail).click();
        page.locator(ccmail).click();
        page.locator(checkall).click();
        page.locator(tomail).click();
        page.locator(checkall).click();
        page.locator(savenotificationmail).click();
        page.locator(notificationalert).click();
        page.locator(notificationsuccesstoastmail).click();
        //page.locator(checkall).click();
        page.locator(checklist).click();
        page.locator(figurechecklist).click();
        page.locator(Checklistsubmitbutton).click();
        page.locator(checlistalert).click();
        page.locator(checklisttoast).click();

        Locator Check = page.locator(addarticlebutton);
        boolean isVisible = Check.isVisible();
        boolean isEnabled = Check.isEnabled();

        if (isVisible && isEnabled) {
            System.out.println("The button is clickable.");
        } else {
            System.out.println("The button is not clickable.");
        }
        page.locator(addarticlebutton).click();
        Thread.sleep(10000);

        page.locator(manageMenu_Art).click();
        page.locator(ArtView_1).click();
        page.locator(ArtView_2).click();
        page.locator("(//em[text()='" + doivalue + "'])[1]//preceding::td[2]").click();
        return doivalue;


    }

    public List<String> modificationOfTATbyAddArticleTwoJournals(String Pub, String J_acrm, String J_name, String PubName,
                                                                 String a, String b, String c, String d, String e, String f, String g,
                                                                 String h, String i, String j, String k, String l, String m, String n,
                                                                 String o, String p, String q, String r, String s, String t, String u,
                                                                 String v, String w, String x, String y, String z, String aa, String bb,
                                                                 String cc, String dd, String ee, String ff, String gg, String hh,
                                                                 String ii, String jj, String artname, String journalacro, String workflow) throws InterruptedException {

        generalPubAcroWithJname(Pub, J_acrm, J_name);
        page.locator(importPubTAT_button).click();
        page.locator(importPubTATyes).click();

        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ems_journal.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("GuidelinesNew.docx"));


//        page.locator(addbutton).click();
        page.waitForTimeout(2000);
        page.evaluate("window.scrollBy(0, 1000)");
        Locator Add = page.locator(addButton);
        boolean isVisible = Add.first().isVisible();
        System.out.println("is Add button visible now? : " + isVisible);


        page.waitForSelector("//button[@type='button']");
        Add.click();

        page.waitForTimeout(2000);
        page.locator(alertCloseButton).click();
        page.waitForTimeout(2000);
        page.locator(manageMenu).click();
        page.locator(JourView_1).click();
        page.locator(JourView_2).click();
        editJournals(PubName, J_acrm, J_name);

        //Modify General TAT from Edit journals
        page.locator(G_FPdaysOfLatex).fill(a);
        page.locator(G_FPdaysOfGraphics).fill(b);
        page.locator(G_FPdaysOfPreEdit).fill(c);
        page.locator(G_FPdaysOfCopyEdit).fill(d);
        page.locator(G_FPdaysOfTypeSetting).fill(e);
        page.locator(G_FPdaysOfQC).fill(f);

        page.locator(G_AUdaysOfPage).fill(g);
        page.locator(G_AUdaysOfQC).fill(h);

        page.locator(G_PEdaysOfPage).fill(i);
        page.locator(G_PEdaysOfQC).fill(j);

        page.locator(G_ONFdaysOfPage).fill(k);
        page.locator(G_ONFdaysOfQC).fill(l);
        page.locator(G_ONFdaysOfXML).fill(m);

        page.locator(G_ISSdaysOfPage).fill(n);
        page.locator(G_ISSdaysOfQC).fill(o);

        page.locator(G_PrintdaysOfPage).fill(p);
        page.locator(G_PrintdaysOfQC).fill(q);
        page.locator(G_PrintdaysOfXML).fill(r);

        //Modify fasttrack TAT from edit journals
        page.locator(FS_FPdaysOfLatex).fill(s);
        page.locator(FS_FPdaysOfGraphics).fill(t);
        page.locator(FS_FPdaysOfPreEdit).fill(u);
        page.locator(FS_FPdaysOfCopyEdit).fill(v);
        page.locator(FS_FPdaysOfTypeSetting).fill(w);
        page.locator(FS_FPdaysOfQC).fill(x);

        page.locator(FS_AUdaysOfPage).fill(y);
        page.locator(FS_AUdaysOfQC).fill(z);

        page.locator(FS_PEdaysOfPage).fill(aa);
        page.locator(FS_PEdaysOfQC).fill(bb);

        page.locator(FS_ONFdaysOfPage).fill(cc);
        page.locator(FS_ONFdaysOfQC).fill(dd);
        page.locator(FS_ONFdaysOfXML).fill(ee);

        page.locator(FS_ISSdaysOfPage).fill(ff);
        page.locator(FS_ISSdaysOfQC).fill(gg);

        page.locator(FS_PrintdaysOfPage).fill(hh);
        page.locator(FS_PrintdaysOfQC).fill(ii);
        page.locator(FS_PrintdaysOfXML).fill(jj);

        page.locator(UpdateJournal).click();
        page.locator(closeUpdateJournal).click();

        generaladdArticle(journalacro, artname, workflow);


        page.locator(TATShow).click();
        page.locator(trackType).click();
        page.locator(GenTrack).click();
        page.locator(importtatfromjournal).click();
        page.locator(confirmimportfromjour).click();


        List<String> NewGeneralEditedValues = new ArrayList<>();

        NewGeneralEditedValues.add(page.locator(daysforlatexnormalization).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforgraphics).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforPreediting).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforcopyediting).inputValue());
        NewGeneralEditedValues.add(page.locator(daysfortypesettings).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforqc).inputValue());

        NewGeneralEditedValues.add(page.locator(daysforaupag).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforauqc).inputValue());

        NewGeneralEditedValues.add(page.locator(daysforpepag).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforpeqc).inputValue());

        NewGeneralEditedValues.add(page.locator(daysforonlinepag).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforonlineqc).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforonlinexml).inputValue());

        NewGeneralEditedValues.add(page.locator(daysforIssuePag).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforIssueQC).inputValue());

        NewGeneralEditedValues.add(page.locator(daysforprintpag).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforprintQC).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforprintxml).inputValue());

        page.waitForTimeout(2000);
        page.locator(trackType).click();
        page.locator(FastTrack).click();
        page.locator(importtatfromjournal).click();
        page.locator(confirmimportfromjour).click();

        List<String> NewFasttrackEditedValues = new ArrayList<>();

        NewFasttrackEditedValues.add(page.locator(daysforlatexnormalization).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforgraphics).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforPreediting).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforcopyediting).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysfortypesettings).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforqc).inputValue());

        NewFasttrackEditedValues.add(page.locator(daysforaupag).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforauqc).inputValue());

        NewFasttrackEditedValues.add(page.locator(daysforpepag).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforpeqc).inputValue());

        NewFasttrackEditedValues.add(page.locator(daysforonlinepag).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforonlineqc).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforonlinexml).inputValue());

        NewFasttrackEditedValues.add(page.locator(daysforIssuePag).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforIssueQC).inputValue());

        NewFasttrackEditedValues.add(page.locator(daysforprintpag).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforprintQC).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforprintxml).inputValue());

        List<String> combinedValuesofTAT = new ArrayList<>();
        combinedValuesofTAT.addAll(NewGeneralEditedValues);
        combinedValuesofTAT.addAll(NewFasttrackEditedValues);

        return combinedValuesofTAT;


    }

    public List<String> unmodifiedTATbyAddArticleTwoJournals(String Pub, String J_acrm, String J_name, String PubName, String journalacro, String artname, String workflow) throws InterruptedException {


        generalPubAcroWithJname(Pub, J_acrm, J_name);
        page.locator(importPubTAT_button).click();
        page.locator(importPubTATyes).click();

        fileChooser = page.waitForFileChooser(() -> page.locator(styletemplate).click());
        fileChooser.setFiles(Paths.get("ems_journal.sty"));

        fileChooser = page.waitForFileChooser(() -> page.locator(guidelinesdoc).click());
        fileChooser.setFiles(Paths.get("GuidelinesNew.docx"));


//        page.locator(addbutton).click();


        page.waitForTimeout(2000);
        page.evaluate("window.scrollBy(0, 1000)");
        Locator Add = page.locator(addButton);
        boolean isVisible = Add.first().isVisible();
        System.out.println("is Add button visible now? : " + isVisible);

        page.waitForSelector("//button[@type='button']");
        Add.click();

        page.waitForTimeout(2000);
        page.locator(alertCloseButton).click();
        page.waitForTimeout(2000);
        page.locator(manageMenu).click();
        page.locator(JourView_1).click();
        page.locator(JourView_2).click();
        editJournals(PubName, J_acrm, J_name);

        generaladdArticle(journalacro, artname, workflow);

        page.locator(TATShow).click();
        page.locator(trackType).click();
        page.locator(GenTrack).click();
        page.locator(importtatfromjournal).click();
        page.locator(confirmimportfromjour).click();


        List<String> NewGeneralEditedValues = new ArrayList<>();

        NewGeneralEditedValues.add(page.locator(daysforlatexnormalization).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforgraphics).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforPreediting).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforcopyediting).inputValue());
        NewGeneralEditedValues.add(page.locator(daysfortypesettings).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforqc).inputValue());

        NewGeneralEditedValues.add(page.locator(daysforaupag).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforauqc).inputValue());

        NewGeneralEditedValues.add(page.locator(daysforpepag).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforpeqc).inputValue());

        NewGeneralEditedValues.add(page.locator(daysforonlinepag).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforonlineqc).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforonlinexml).inputValue());

        NewGeneralEditedValues.add(page.locator(daysforIssuePag).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforIssueQC).inputValue());

        NewGeneralEditedValues.add(page.locator(daysforprintpag).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforprintQC).inputValue());
        NewGeneralEditedValues.add(page.locator(daysforprintxml).inputValue());

        page.waitForTimeout(2000);
        page.locator(trackType).click();
        page.locator(FastTrack).click();
        page.locator(importtatfromjournal).click();
        page.locator(confirmimportfromjour).click();

        List<String> NewFasttrackEditedValues = new ArrayList<>();

        NewFasttrackEditedValues.add(page.locator(daysforlatexnormalization).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforgraphics).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforPreediting).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforcopyediting).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysfortypesettings).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforqc).inputValue());

        NewFasttrackEditedValues.add(page.locator(daysforaupag).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforauqc).inputValue());

        NewFasttrackEditedValues.add(page.locator(daysforpepag).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforpeqc).inputValue());

        NewFasttrackEditedValues.add(page.locator(daysforonlinepag).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforonlineqc).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforonlinexml).inputValue());

        NewFasttrackEditedValues.add(page.locator(daysforIssuePag).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforIssueQC).inputValue());

        NewFasttrackEditedValues.add(page.locator(daysforprintpag).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforprintQC).inputValue());
        NewFasttrackEditedValues.add(page.locator(daysforprintxml).inputValue());

        List<String> combinedValuesUnmodifiedofTAT = new ArrayList<>();
        combinedValuesUnmodifiedofTAT.addAll(NewGeneralEditedValues);
        combinedValuesUnmodifiedofTAT.addAll(NewFasttrackEditedValues);

        return combinedValuesUnmodifiedofTAT;


    }

}


