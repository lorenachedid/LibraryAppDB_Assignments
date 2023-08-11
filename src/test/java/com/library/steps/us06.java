package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class us06 {

    BookPage bookPage = new BookPage();

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        bookPage.addBook.click();



    }


    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String string) {

        bookPage.bookName.sendKeys(string);

    }


    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String string) {

        bookPage.isbn.sendKeys(string);
    }


    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String string) {
        bookPage.year.sendKeys(string);

    }


    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String string) {

        bookPage.author.sendKeys(string);

    }

    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String string) {

        Select select = new Select(bookPage.categoryDropdown);
        select.selectByVisibleText(string);

    }



    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
       bookPage.saveChanges.click();
    }


    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String string) {

        Assert.assertTrue(bookPage.toastMessage.isDisplayed());
    }


    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String expectedBookName) {

    String query = "select name, isbn,year, author from books\n" +
            "where name = '"+ expectedBookName + "'";

        DB_Util.runQuery(query);

        Map<String, String> dbBookInfo = DB_Util.getRowMap(1);

        String actualBookName = dbBookInfo.get("name");

        Assert.assertEquals(expectedBookName, actualBookName);


    }


}
