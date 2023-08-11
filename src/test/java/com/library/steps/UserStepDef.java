package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserStepDef {

    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBorrowedBooks;
    BookPage bookPage = new BookPage();
    List<String> actualList;
    String actualUserCount;

    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        // DB_Util.createConnection();
        System.out.println("---Connection will be done before hook----");
    }

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {

        String query = "select count(id) from users";
        DB_Util.runQuery(query);

        actualUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(actualUserCount);

    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        String query = "select count(distinct id) from users";
        DB_Util.runQuery(query);

        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(expectedUserCount);

        Assert.assertEquals(expectedUserCount, actualUserCount);

        System.out.println("---Connection will be closed after hook----");

    }


    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {

        String query = "select * from users";
        DB_Util.runQuery(query);
        actualList = DB_Util.getAllColumnNamesAsList();

    }

    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedList) {


        Assert.assertEquals(expectedList, actualList);


    }

    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {
        actualBorrowedBooks = dashBoardPage.borrowedBooksNumber.getText();


    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
       DB_Util.runQuery("select count(*) from book_borrow where is_returned=0");

        String expectedBorrowedBooks = DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(expectedBorrowedBooks,actualBorrowedBooks);
    }



    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
    bookPage.mainCategoryElement.click();


    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {

        List<String> actualCategories = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);

        actualCategories.remove(0); // to take away first 'All'

        String query = "select name from book_categories";
        DB_Util.runQuery(query);


        List<String> expectedBookcategories = DB_Util.getColumnDataAsList(1);

        Assert.assertEquals(expectedBookcategories,actualCategories);

    }

}
