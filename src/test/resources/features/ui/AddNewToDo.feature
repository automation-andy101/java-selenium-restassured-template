Feature: Add a new todo feature

  As a Todo application user
  I want to be able to add new todos
  so that I have a list of items that I need to complete

  @DeleteCreatedTodoAfterTest
  @critical
  Scenario: Add a new todo
    Given the todo list is empty
    When I enter "Add a todo" into the add new todo input element
    And select the current date
    And click the Add button
    Then new todo "Add a todo" appears in the todo list

  @critical
  Scenario: Delete a todo
    Given a new todo has been created with the following data
      | name      | isComplete | dueDate     |
      | TEST TODO | false      | DATETIMENOW |
    When I click the Delete button for todo with name "TEST TODO"
#    Then todo with name "TEST TODO" no longer appears in todo table


