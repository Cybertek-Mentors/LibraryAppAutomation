@add_book_api
Feature: Add books

  Scenario: 1. Add book
    Given user makes API call to add a book
      | name          | isbn       | year | author               | book_category_id | description                                                            |
      | Infinite Jest | 0316066524 | 1996 | David Foster Wallace | 1                | Infinite Jest is a 1996 novel by American writer David Foster Wallace. |
