**TDD TestNG Project**

**SELENIUM-TestNG-JAVA-MAVEN**

This repository contains a sample project that demonstrate how to use a selenium-testNG-java, a TDD (Test Driven Development) with TestNG and JAVA.
This Project showcase automation script development and utiliize various reports such as default testng reports, Extent-spark reports and allure reports.
Additionally, it offers the ability to capture screenshots for tests and generate error shots for failed test cases.

**INSTALLATION & PREREQUISITIES**
1.JDK(1.8+)

2.Maven

3.IntelliJ IDEA

4.Required Plugins

  Maven
  
  TestNG

**WHAT ARE ALL IMPLEMENTED IN FRAMEWORK**

1. Page Object Design Pattern.
2. TestNG TDD feature for executing test cases
3. Utility functions to handle Driver methods.
4. Extent and Allure reports for test execution details.
5. Docker - to execute the tests in light weight containers

**GETTING STARTED**

To setup the framework you can either clone the repository or download the zip file and set it up in your local workspace.

Import the project using eclipse or Intellij

**RUNNING THE TESTS**

Go to your project directory from commandprompt and hit following commands:

mvn clean install

mvn clean install -Dbrowser="chrome"(to use any other browser)

mvn clean install -Denv="qa"(to use any other environment)

if you are familiar with docker you can user the docker-compose yml file to run the tests in selenium grid

Use this command

docker-compose up -d
