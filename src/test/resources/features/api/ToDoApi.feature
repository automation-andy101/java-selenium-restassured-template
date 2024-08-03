Feature: Todo API
  As a user of the Todo application,
  I want to be able to manage my tasks through the API,
  So that I can efficiently create, read, update, and delete my todos.

  Scenario: Get all todos
    When I send a GET request to retrieve all todos
    Then the response status code for getting all Todos is 200
    And the response contains a list of todos

  Scenario: Get a single todo by ID
    Given I send a GET request to retrieve all todos
    And I extract the ID of the first todo in the list
    When I send a GET request for the todo with the extracted ID
    Then the response status code for getting a single Todo is 200
    And the response contains the details of the todo with the requested ID

  Scenario: Create a new todo
    When I send a POST request to create a new todo with the following data
      | name          | isComplete | dueDate     |
      | Shave my legs | false      | DATETIMENOW |
    Then the response status code for creating a new Todo is 201
    And the response should contain the created todo with name "Shave my legs" and isComplete set to "false"

  Scenario: Update a todo
    Given I send a GET request to retrieve all todos
    And I extract the ID of the first todo in the list
    When I send a PUT request to update the todo with the following data
      | name         | isComplete |
      | Updated Todo | true       |
    Then the response status code for updating a Todo is 204

  Scenario: Delete a todo
    Given I send a GET request to retrieve all todos
    And I extract the ID of the first todo in the list
    When I send a DELETE request to remove the todo
    Then the response status code for deleting a Todo is 204

  Scenario: Error when getting a non-existent todo
    When I send a GET request to retrieve a todo with ID 99999
    Then the response status code for getting a single Todo is 404

  Scenario: Error when creating a todo with missing 'name field value
    When I send a POST request to create a new todo with missing data
      | name | isComplete | dueDate     |
      |      | false      | DATETIMENOW |
    Then the response status code for creating a new Todo with missing data is 400
    And the response should contain error message "The Name field is required."

