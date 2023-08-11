package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
//import io.cucumber.java.en.Given;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.en.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class StudentsBorrowNewBook {

    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
    public static String currentBookName;



    @Given("the {string} on the home page")
    public void the_on_the_home_page(String string) {
        loginPage.login(string);


    }
    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        bookPage.navigateModule(moduleName);

    }

    @Given("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {
        currentBookName = bookName;
    bookPage.search.sendKeys(bookName);

    }
    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {

    bookPage.borrowBook(currentBookName).click();

    }


    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String borrowPage) {
    bookPage.navigateModule(borrowPage);


    Assert.assertTrue(borrowedBooksPage.returnBook.isDisplayed());


    }


    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {

        String query = "SELECT full_name, b.name, bb.borrowed_date from users u join book_borrow bb on u.id = bb.user_id\n" +
                "join books b on bb.book_id = b.id\n" +
                "where full_name = 'Test Student 55' and name = 'Lorena Book'\n" +
                "order by  3 desc";

        DB_Util.runQuery(query);

        String checkedOutBook = borrowedBooksPage.checkedOutBook.getText();
        String checkoutDate = borrowedBooksPage.checkedOutBookBorrowedDate.getText();
        String studentName = borrowedBooksPage.accountHolderName.getText();

        Map<String, String> bookInfo = DB_Util.getRowMap(1);

        String expectedStudentDB = bookInfo.get("full_name");
        String expectedBookNameDB = bookInfo.get("name");
        String expectedBorrowedDateDB = bookInfo.get("borrowed_date");

        Assert.assertEquals(expectedStudentDB,studentName);
        Assert.assertEquals(expectedBookNameDB,checkedOutBook);
        Assert.assertEquals(expectedBorrowedDateDB,checkoutDate);

    }


    @Then("user returns book")
    public void user_returns_book() {

        borrowedBooksPage.returnBook.click();

    }


}
