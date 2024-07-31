package org.jms.pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePage {

    private Page page;
    private String baseicon = "//img[@src='/jms/src/assets/GeneralIcons/shortcuts.svg']";
    private String addpubicon = "id=add_publisher";
    private String addjournalicon = "id=add_journal";


    public HomePage(Page page) {
        this.page = page;
    }


    public String CheckUrl() {
        return page.url();
    }

    public String CheckTitle() {
        return page.title();
    }

    public boolean isBaseIconDisplayed() {

        assertThat(page.locator(baseicon)).isAttached();
        return page.locator(baseicon).isVisible();


    }

    public AddJournalPage navigateToAddJournalpage() {

        page.url();

        return new AddJournalPage(page);
    }



    public PreRequestPage navigateToCommonpage() {

        page.url();

        return new PreRequestPage(page);


    }

    public UserManagementPage navigateToUserManagementPage() {

        page.url();

        return new UserManagementPage(page);
    }

}
