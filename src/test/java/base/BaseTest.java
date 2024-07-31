package base;

import com.microsoft.playwright.Page;
import listeners.ExtentReportListener;
import org.jms.pagefactory.PageFactory;
import org.jms.pages.AddJournalPage;
import org.jms.pages.HomePage;
import org.jms.pages.PreRequestPage;
import org.jms.pages.UserManagementPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import java.io.IOException;
import java.util.Properties;


public class BaseTest {
    Page page;
    PageFactory pf;
    protected Properties prop;
    protected HomePage homepage;
    protected AddJournalPage addJournalPage;
    protected PreRequestPage prerequestpage;
    protected UserManagementPage userManagement;





    @BeforeClass
    public void startBrowser() throws IOException {
        pf = new PageFactory();
        prop = pf.initProp();
        page = pf.initBrowser(prop);
        homepage = new HomePage(page);
        ExtentReportListener.setPage(page);

    }

    @AfterClass
    public void tearDown() throws InterruptedException {

        //   page.context().tracing().stopChunk(new Tracing.StopChunkOptions().setPath(Paths.get("trace.zip")));


        page.context().browser().close();
    }


    //New

}
