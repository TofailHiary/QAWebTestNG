
# Automated Testing Framework Documentation

## Overview

This automated testing framework, built using Java 11, is tailored for web testing with Selenium WebDriver. It integrates TestNG as the testing framework and WebDriverManager for browser driver management. The framework features Extent Reports for sophisticated test reporting and utilizes Excel for managing test data and results. It is ideal for data-driven testing methodologies and offers robust capabilities for browser interaction, user management, and detailed test reporting.

## Framework Structure

The framework consists of several key components, organized into packages:

### 1. `com.DriverManager`
   - **ReadProperty**: Manages configuration from properties files for different testing environments.
   - **SetupDriver**: Handles WebDriver initialization and teardown, facilitating browser interactions.

### 2. `com.utilities`
   - **ReadWriteTestCases**: Handles interactions with Excel files for managing test data and recording results.

### 3. `testData`
   - **Constants**: Stores constant values such as file paths, error messages, and other static data for tests.
   - **UsersManager**: Manages user credentials, supporting authentication testing.

### 4. TestNG Integration
   - Provides a robust testing framework, supporting annotations, grouping, and parallel execution of tests.

### 5. WebDriverManager Integration
   - Automates the management of browser drivers, ensuring compatibility and ease of setup.

### 6. Extent Reports Integration
   - Generates comprehensive, interactive reports detailing test execution, results, and logs.

## Key Features

- **Java 11 and TestNG**: Modern Java development with the extensive testing capabilities of TestNG.
- **Selenium WebDriver and WebDriverManager**: Automated web interactions with easy driver management.
- **Excel-Based Test Management**: Efficiently manages test cases and results within Excel for data-driven approaches.
- **User Authentication Testing**: Predefined user credentials for testing various authentication flows.
- **Configurable Environment**: Flexibility in test configuration via properties files.
- **Detailed Reporting with Extent Reports**: Rich, interactive test reports for in-depth analysis.

## Usage Guidelines

1. **Configuration**: Set up the testing environment in `config.properties`.
2. **Test Data Management**: Utilize `ReadWriteTestCases` for managing test cases and results in Excel.
3. **Browser Setup**: Use `SetupDriver` for browser interactions, leveraging WebDriverManager for driver setup.
4. **User Management**: Employ `UsersManager` for handling user credentials in tests.
5. **Test Execution and Reporting**: Run tests using TestNG and generate detailed reports with Extent Reports.

## Reporting

Extent Reports provide a detailed view of test executions, including outcomes, timings, environment details, and failure points with screenshots. These reports are vital for identifying issues and assessing test coverage and quality.

## Additional Information

- Ensure the Java 11 environment is correctly set up and compatible with all framework components.
- Customize Extent Reports as per project requirements for more tailored reporting.
- The framework is designed to be modular, allowing for future integrations and scalability.
