package com.cybertekschool.library.step_definitions;

import com.cybertekschool.library.api.BooksAPI;
import com.cybertekschool.library.database.BooksDB;
import com.cybertekschool.library.pages.BooksPage;
import com.cybertekschool.library.pojos.Book;
import com.cybertekschool.library.utils.api.AuthenticationUtility;
import com.cybertekschool.library.utils.api.Endpoints;
import com.cybertekschool.library.utils.api.LibrarianAuthenticationUtility;
import com.cybertekschool.library.utils.db.DBUtils;
import com.cybertekschool.library.utils.ui.BrowserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class BooksStepDefs extends BaseStep {
    BooksAPI booksAPI = new BooksAPI();
    BooksPage booksPage = new BooksPage();
    BooksDB booksDB = new BooksDB();

    @Then("book information must match the api for {}")
    public void book_information_must_match_the_api_for_The_kite_runner(String book) {
        // call the database to get the book id for
        String query = "SELECT id FROM books WHERE name = '" + book + "'";
        String id = DBUtils.getCellValue(query).toString();
        // get the token
        AuthenticationUtility authenticationUtility = new LibrarianAuthenticationUtility();
        String token = authenticationUtility.getToken();
        // use the id to make the call to api
        Response response = given().
                log().all().
                header("x-library-token", token).
                pathParam("id", id).
                when().
                get(Endpoints.GET_BOOK_BY_ID).
                prettyPeek();
        // verify response vs ui
        response.then().statusCode(200).contentType(ContentType.JSON);
        Book bookPojo = response.as(Book.class);

        assertThat(bookPojo.getName(), is(booksPage.bookName.getAttribute("value")));
        assertThat(bookPojo.getAuthor(), is(booksPage.author.getAttribute("value")));
        assertThat(bookPojo.getIsbn(), is(booksPage.isbn.getAttribute("value")));
        assertThat(bookPojo.getDescription(), is(booksPage.description.getAttribute("value")));

    }

    @When("I open book {}")
    public void i_edit_book_The_kiterunner(String book) {
        System.out.println("book = " + book);
        BrowserUtils.waitForClickability(booksPage.search, 5).sendKeys(book);
        BrowserUtils.waitForClickability(booksPage.editBook(book), 5).click();

    }

    @Then("book information must match the database for {}")
    public void book_information_must_match_the_database_for_The_kite_runner(String book) {
//        DBUtils.getColumnNames(sql);
        Map<String, Object> dbData = booksDB.getBookInfoFromDB(book);
        assertEquals("author did not match", dbData.get("author").toString(), booksPage.author.getAttribute("value"));
        assertEquals("year did not match", dbData.get("year").toString(), booksPage.year.getAttribute("value"));
        assertEquals("isbn did not match", dbData.get("isbn").toString(), booksPage.isbn.getAttribute("value"));
        assertEquals("description did not match", dbData.get("description").toString(), booksPage.description.getAttribute("value"));
        assertEquals("category did not match", dbData.get("name").toString(), booksPage.categoryList().getFirstSelectedOption().getText());
    }

    @Then("book categories must match book_categories table from db")
    public void book_categories_must_match_book_categories_table_from_db() {
        // get the expected categories from the database as a list
        String sql = "SELECT name FROM book_categories;";
        List<Object> namesObj = DBUtils.getColumnData(sql, "name");
        List<String> exNames = new ArrayList<>();
        for (Object o : namesObj) {
            exNames.add(o.toString());
        }
        // get the actual categories from UI as webelements
        // convert the web elements to list
        List<WebElement> optionsEl = booksPage.mainCategoryList().getOptions();
        List<String> acNames = BrowserUtils.getElementsText(optionsEl);
        // remove the first option ALL from acList.
        acNames.remove(0);
        // compare 2 lists
        assertEquals("Categories did not match", exNames, acNames);
    }

    @Given("user makes API call to add a book")
    public void user_makes_API_call_to_add_a_book(List<Map<String, String>> books) {
        books.forEach(book -> {
            Response response = booksAPI.addBook(book).prettyPeek();
            response.then().assertThat().body("message", is("The book has been created."));
        });
    }
}
