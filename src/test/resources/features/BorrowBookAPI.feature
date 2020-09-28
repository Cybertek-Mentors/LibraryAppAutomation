@borrow_book_api
Feature: Borrow book

  Scenario: Borrow a book
    Given user makes API call to borrow a book
    Then verify response body has "The book has been borrowed" message