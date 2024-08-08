Feature: Add a new todo feature

  As a Todo application user
  I want to be able to add new todos
  so that I have a list of items that I need to complete

  @DeleteCreatedTodoAfterTest
  @critical
  Scenario: Add a new todo item
    Given the todo list is empty
    When I enter "Add a todo" into the add new todo input element
    And select the current date
    And click the Add button
    Then new todo "Add a todo" appears in the todo list

