package com.library.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BorrowedBooksPage extends BasePage{


    @FindBy(xpath = "//tbody//td[2]")
    public List<WebElement> allBorrowedBooksName;

    @FindBy(xpath = "//table/tbody/tr/td[1]/a")
    public WebElement borrowBook;

    @FindBy(xpath = "//td[6][.='NOT RETURNED ']/../td/a")
    public WebElement returnBook;

    @FindBy(xpath = "//td[6][.='NOT RETURNED ']/preceding-sibling::td[3]")
    public WebElement checkedOutBookBorrowedDate;

    @FindBy(xpath = "//td[6][.='NOT RETURNED ']/preceding-sibling::td[4]")
    public WebElement checkedOutBook;

}

