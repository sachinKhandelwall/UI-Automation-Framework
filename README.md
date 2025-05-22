
##  Cloudbees UI Automation

This 'cloudbeesUiAutomationAssignment' repo contains the source code to automate and validate the following steps:

Open the application 'https://www.cloudbees.com/'
Click the link Products on top > Click CloudBees CD/RO under Other Products
Verify that Cost Savings has a value of $2m
Scroll down, click Auditors / Security
Verify the text under Release Governance (Generate single-click audit reports)
Click the link Resources on top > Click Documentation
Verify that it opens a new tab
Click in the text field Search all CloudBees Resources
Verify that a full page modal is opened
Search for the word "Installation"
Verify that we have pagination options at bottom


## Assumptions

- Java 17 or higher version is installed
- Created Maven based Java project
- Used TestNG as a testing framework and Allure for reporting.

## How It Works

- src/main/java contains two packages, pages and utils
- pages package contains 3 Classes, each class is responsible for their page objects, locators and methods to be utilized in the test cases.
- utils contains ElementUtil, PropertyUtil and LogUtils Class 
- ElementUtil Class consists of multiple methods that required to operate on driver for finding, clicking and entering something on web elements
- PropertyUtils Class is responsible for fetching the properties provided in the browser.properties file in src/test/resources folder
- LogUtils Class is responsible for providing methods for logging  like info, error, debug, etc which utilizes the log4j2.xml configuration present in src/test/resources folder
- src/test/java contains 2 packages base and tests
- base package has BaseTest.java Class which contains @BeforeClass where the browser is set up and page objects are initialized
- Browser property like for which browser we want to execute our test suite is fetched from the browser.properties file in src/test/resources folder
- Cloudbees url to open after the driver initialization is also fetched from the browser.properties file in src/test/resources folder.
- test package has CloudBeesTest.java class which is our main test class for running the testNg test cases
- used allure reporting for showing all the steps of the test case in the report
- logged all the steps of the test case using log4j
- added all the test cases of a class to be run in testng.xml to execute in one go

## Run Instructions

- Run 'mvn clean test -DsuiteXmlFile=testng.xml' for executing all the test cases configured in testng.xml

## Allure Report

- Run 'mvn allure:report' command to generate the allure report after the test case results once testng.xml file is executed.
- This allure report will be generated tо directory: target/site/allure-maven/index.html
