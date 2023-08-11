package com.library.steps;

import com.google.common.base.Verify;
import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class verifyBookInformationWithDB {

    BookPage bookPage = new BookPage();

    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
                            //String
        bookPage.borrowBook(StudentsBorrowNewBook.currentBookName).click();
        BrowserUtil.waitFor(2);

        //    book.the_user_searches_for_book(book.currentBookName);

    }

    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {

        String actualBookName = bookPage.bookName.getAttribute("value");
        String actualISBN = bookPage.isbn.getAttribute("value");
        String actualYear = bookPage.year.getAttribute("value");
        String actualAuthor = bookPage.author.getAttribute("value");

      //  System.out.println("actualAuthor = " + actualAuthor);

        String query = "select * from books where name ='Lorena Book'";
        DB_Util.runQuery(query);

        Map<String, String> dbBookInfo = DB_Util.getRowMap(1);

        String expectedBookName = dbBookInfo.get("name");
        String expectedISBN = dbBookInfo.get("isbn");
        String expectedYear = dbBookInfo.get("year");
        String expectedAuthor = dbBookInfo.get("author");

       // System.out.println("expectedAuthor = " + expectedAuthor);

        Assert.assertEquals(expectedBookName,actualBookName);
        Assert.assertEquals(expectedISBN,actualISBN);
        Assert.assertEquals(expectedYear,actualYear);
        Assert.assertEquals(expectedAuthor,actualAuthor);


    }
}