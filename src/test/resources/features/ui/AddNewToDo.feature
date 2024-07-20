Feature: Add a new todo feature

  As a Todo application user
  I want to be able to add new todos
  so that I have a list of items that I need to complete

  Background:
    Given I am on the Todo app homepage
    And the todo list is empty

    @DeleteCreatedTodoAfterTest
    Scenario: Add a new todo item
      When I enter "Walk the dog" into the add new todo input element
      And click the Add button
      Then text reads "1 to-do"
      And new todo "Walk the dog" appears in the todo list

