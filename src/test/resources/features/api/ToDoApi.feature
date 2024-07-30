Feature: Todo API
  As a user of the Todo application,
  I want to be able to manage my tasks through the API,
  So that I can efficiently create, read, update, and delete my todos.

#  Scenario: Get all todos
#    When I send a GET request to retrieve all todos
#    Then the response status code for getting all Todos is 200
#    And the response contains a list of todos
#
#  Scenario: Get a single todo by ID
#    Given I send a GET request to retrieve all todos
#    And I extract the ID of the first todo in the list
#    When I send a GET request for the todo with the extracted ID
#    Then the response status code for getting a single Todo is 200
#    And the response contains the details of the todo with the requested ID

  Scenario: Create a new todo
    When I send a POST request to create a new todo with the following data
      | name          | isComplete | dueDate     |
      | Shave my legs | false      | DATETIMENOW |
    Then the response status code for creating a new Todo is 201
    And the response should contain the created todo with name "Shave my legs" and isComplete set to "false"

#  Scenario: Update a todo
#    Given the API is running
#    And a todo with ID 1 exists
#    When I send a PUT request to "/todos/1" with the following data
#      | title         | description              |
#      | "Updated Todo" | "This is an updated todo" |
#    Then the response status code should be 200
#    And the response should be in JSON format
#    And the response should contain the updated todo with title "Updated Todo" and description "This is an updated todo"
#
#  Scenario: Delete a todo
#    Given the API is running
#    And a todo with ID 1 exists
#    When I send a DELETE request to "/todos/1"
#    Then the response status code should be 204
#    And the todo with ID 1 should no longer exist
#
#  Scenario: Error when getting a non-existent todo
#    Given the API is running
#    When I send a GET request to "/todos/999"
#    Then the response status code should be 404
#    And the response should be in JSON format
#    And the response should contain an error message "Todo not found"
#
#  Scenario: Error when creating a todo with missing fields
#    Given the API is running
#    When I send a POST request to "/todos" with the following data
#      | title | description   |
#      |       | "Missing title" |
#    Then the response status code should be 400
#    And the response should be in JSON format
#    And the response should contain an error message "Title is required"
