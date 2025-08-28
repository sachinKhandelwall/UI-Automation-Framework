# 🚀 UI Automation Suite

This repository contains an automated UI test suite built using **Selenium WebDriver**, **Java**, **TestNG**, and **Allure Reporting** to validate key user flows on the [CloudBees website](https://www.cloudbees.com/).

---

## 🔍 Test Scenarios Covered

The automation validates the following sequence of interactions:

1. **Launch** the application at `https://www.cloudbees.com/`
2. **Navigate** via top menu:
    - Click **Products**
    - Under *Other Products*, click **CloudBees CD/RO**
3. **Verify** cost savings section displays `$2m`
4. **Scroll and interact** with the **Auditors / Security** section
5. **Verify** text under *Release Governance*:
   > _"Generate single-click audit reports"_
6. **Navigate** to **Resources > Documentation**
7. **Verify** a new tab is opened for Documentation
8. **Click** the search box: *Search all CloudBees Resources*
9. **Verify** a full-page modal opens
10. **Search** for the term `"Installation"`
11. **Verify** pagination options are visible at the bottom of the results

---

## ✅ Assumptions

- Java **17** or higher is installed
- Maven is installed and configured

---

## ⚙️ Tech Stack & Framework Structure

### 🔸 Technologies Used
- **Java 17**
- **Selenium WebDriver**
- **TestNG** (Test Framework)
- **Allure** (Test Reporting)
- **Log4j2** (Logging)
- **Maven** (Build Management)

---

### 🔸 Project Structure

```
CloudbeesUiAutomation/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── drivers/            # DriverFactory for WebDriver initialization
│   │       ├── pages/              # Page Object Models for each webpage
│   │       ├── utils/              # Utility classes: ScreenshotUtil, PropertyUtils, LogUtils, ElementUtil
│   │       └── listeners/          # TestNG listeners & RetryTransformer
│   │
│   └── test/
│       └── java/
│           ├── base/               # BaseTest: WebDriver setup and teardown
│           └── tests/              # Test classes (CloudBeesTest)
│
├── src/test/resources/
│   ├── browser.properties          # Configurations (browser, app URL, etc.)
│   └── log4j2.xml                  # Log4j2 configuration
│
├── pom.xml                         # Maven dependencies & plugin config
└── testng.xml                      # TestNG suite file with listeners and retry transformer

```

---


### 🔸 Key Components

#### 🧩 `drivers/`
- **DriverFactory**: Handles WebDriver creation for Chrome, Firefox, Edge, Safari
- Ensures a single WebDriver instance per test class

#### 🧩 `pages/`
- Contains page classes like `HomePage`, `ProductsPage`, and `DocumentationPage`
- Implements **Page Object Model** to encapsulate locators and interactions

#### 🧰 `utils/`
- **ElementUtil**: Common WebDriver operations (click, sendKeys, wait, scroll)
- **PropertyUtils**: Reads values from `browser.properties`
- **LogUtils**: Uses **Log4j2** for logging info, errors, and debug messages
- **ScreenshotUtil**: Takes screenshots on failure and attaches them to Allure reports

#### 🧪 `tests/`
- **CloudBeesTest**: Contains the complete UI test flow for the CloudBees app

#### ⚙️ `base/`
- **BaseTest**: Initializes WebDriver via **DriverFactory** and page objects
  > Supports Chrome, Firefox, Edge, and Safari (using Selenium Manager)  
  > Opens the application before each test and clears cookies after each test

#### 🔔 `listeners/`
- **TestListener**: Captures screenshots on test failure and attaches them to Allure
- **RetryTransformer**: Implements global retry logic for failed test cases

---

## 🚦 How to Run Tests

### 1️⃣ Run all test cases via Maven:

```bash
mvn clean test -DsuiteXmlFile=testng.xml

---

### 2️⃣ Generate Allure Report:

```bash
mvn allure:report
```

Open the report in a browser:

```bash
target/site/allure-maven/index.html
```

---

## 📌 Notes

- Logs are printed to the console using **Log4j2**, configured via `log4j2.xml`
- Browser and application URL are externalized in `browser.properties` for flexibility
- Each test step is tracked both in logs and in **Allure** using `LogUtils` and `Allure.step()`

---

## 👨‍💻 Author

**Sachin Khandelwal**  
🔗 [GitHub Repo](https://github.com/sachinKhandelwall/cloudbeesUiAutomationAssignment)
