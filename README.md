# ToDo Web Application Test Framework

This repository contains an automated test framework for the ToDo web application. The framework utilizes Java, Selenium, Cucumber, and RestAssured to perform UI and API testing.

## Table of Contents

- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running Tests](#running-tests)
- [Project Structure](#project-structure)
- [Writing Tests](#writing-tests)
- [Reporting](#reporting)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Before you begin, ensure you have met the following requirements:

- Java JDK 8 or higher installed on your machine
- Maven installed for managing project dependencies
- ChromeDriver or GeckoDriver for Selenium WebDriver
- Your preferred IDE (IntelliJ IDEA, Eclipse, etc.)

### AUT Installation

1. Clone the application under test (AUT)  repository:

 ```sh
 git clone https://github.com/g33klady/TodoApiSample.git
 ```
2. Navigate to the project directory:

    ```sh
    cd TodoApiSample
    ```
3. Open Docker Desktop and click start on the myapp to start the todo application.


### Automated Test project Installation

1. Clone the automated test repository:

    ```sh
    git clone https://github.com/automation-andy101/java-selenium-restassured-template.git
    ```

2. Navigate to the project directory:

    ```sh
    cd todo-webapp-test-framework
    ```

3. Install the project dependencies:

    ```sh
    mvn clean install
    ```

### Running Tests

You can run the tests using Maven commands.

#### Running All Tests
1. Running all tests using the Chrome browser:

    ```sh
    mvn test -Dbrowser=chrome
    ```

#### Running ONLY API Tests
2. Running all tests using the Chrome browser:

    ```sh
    mvn test -Papi-tests
    ```

#### Running ONLY UI Tests
3. Running all tests using the Chrome browser:

    ```sh
    mvn test -Pui-tests
    ```

#### Running a single api feature file scenario using a tag
4. Running all tests using the Chrome browser:

    ```sh
    mvn test -Pui-tests -Dcucumber.filter.tags="@focus"

    ```

#### Generate Allure reports
1. To generate Allure reports run the following command:

    ```sh
    allure serve C:\Users\andys\Documents\coding\java\spring\java-selenium-restassured-template\target\allure-results

    ```