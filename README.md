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
│   │       ├── pages/              # Page Object Models for each webpage
│   │       └── utils/              # Utility classes for logging, driver ops, etc.
│   └── test/
│       └── java/
│           ├── base/               # BaseTest: WebDriver setup, teardown
│           └── tests/              # Test classes (CloudBeesTest)
│
├── src/test/resources/
│   ├── browser.properties          # Configurations (browser, app URL, etc.)
│   └── log4j2.xml                  # Log4j2 configuration
│
├── pom.xml                         # Maven dependencies & plugin config
└── testng.xml                      # TestNG suite file
```

---

### 🔸 Key Components

#### 🧩 `pages/`
- Contains page classes like `HomePage`, `ProductsPage`, and `DocumentationPage`
- Implements **Page Object Model** to encapsulate locators and interactions

#### 🧰 `utils/`
- `ElementUtil`: Common WebDriver operations (click, sendKeys, wait, scroll)
- `PropertyUtil`: Reads values from `browser.properties`
- `LogUtils`: Uses **Log4j2** for logging info, errors, and debug messages

#### 🧪 `tests/`
- `CloudBeesTest`: Contains the complete UI test flow for the CloudBees app

#### ⚙️ `base/`
- `BaseTest`: Initializes WebDriver (based on `browser.properties`) and page objects
  > Supports Chrome, Firefox, Edge, and Safari (using Selenium Manager)

---

## 🚦 How to Run Tests

### 1️⃣ Run all test cases via Maven:

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

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
