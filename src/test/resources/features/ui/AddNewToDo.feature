Feature: Add a new todo feature

  As a Todo application user
  I want to be able to add new todos
  so that I have a list of items that I need to complete

#  Background:
#    Given I am on the Todo app homepage
#    And the todo list is empty

#    @DeleteCreatedTodoAfterTest
    Scenario: Add a new todo item
#      Given the todo list is empty
      When I enter "Walk the dog" into the add new todo input element
      And select the current date
      And click the Add button
#      Then table row 1 Name column contains "Walk the dog"
#      And new todo "Walk the dog" appears in the todo list

