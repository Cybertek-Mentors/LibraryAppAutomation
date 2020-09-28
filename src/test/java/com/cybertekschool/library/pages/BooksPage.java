package com.cybertekschool.library.pages;

import com.cybertekschool.library.utils.ui.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BooksPage extends BasePage {

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> allRows;

    @FindBy(tagName = "input")
    public WebElement search;

    @FindBy(css = "[href='tpl/add-book.html']")
    public WebElement addBook;

    @FindBy(name = "name")
    public WebElement bookName;

    @FindBy(name = "isbn")
    public WebElement isbn;

    @FindBy(name = "year")
    public WebElement year;

    @FindBy(xpath = "(//input[@type='text'])[4]")
    public WebElement author;

    @FindBy(id = "description")
    public WebElement description;

    @FindBy(id = "book_group_id")
    public WebElement categoryElement;

    @FindBy(id = "book_categories")
    public WebElement mainCategoryElement;

    @FindBy(xpath = "//a[contains(@onclick,'Books.borrow_book')]") //H*************
    public WebElement borrowBookBtn;

    @FindBy(xpath = "//i[contains(@class,'fa fa-angle-right')]") //H*************
    public WebElement nextBtn;

    @FindBy(linkText = "Return Book")               //H*************************
    public List<WebElement> returnBookBtn;

    public WebElement editBook(String book) {
        String xpath = "//td[3][.='" + book + "']/../td/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    public Select categoryList() {
        return new Select(categoryElement);
    }

    public Select mainCategoryList() {
        return new Select(mainCategoryElement);
    }

}
