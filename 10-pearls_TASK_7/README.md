# Assignment 7 – Selenium Automation Framework

Automation framework built with **Java + Selenium WebDriver + TestNG + Maven**,
following the **Page Object Model (POM)** pattern. Target application:
[https://www.saucedemo.com](https://www.saucedemo.com) — a public site built
specifically for practicing Selenium automation (login flows, product catalog,
product detail pages).

## Project Structure

```
selenium-framework/
├── pom.xml                          # Maven dependencies (Selenium, TestNG, WebDriverManager)
├── testng.xml                       # TestNG suite definition
├── src/
│   ├── main/java/
│   │   ├── pages/                   # Page Object Model classes
│   │   │   ├── LoginPage.java
│   │   │   ├── ProductsPage.java
│   │   │   └── ProductDetailsPage.java
│   │   └── utils/
│   │       ├── ActionUtils.java     # Reusable custom commands (click, type, wait, dropdown...)
│   │       └── BaseTest.java        # Driver setup/teardown shared by all tests
│   └── test/java/
│       └── tests/
│           ├── LoginTest.java              # Task 2 + Task 3
│           └── ProductNavigationTest.java  # Task 4
```

## How each task maps to the code

| Task | Where it lives |
|---|---|
| 1. Setup framework | `pom.xml`, `BaseTest.java` |
| 2. Login failure scenarios | `LoginTest.testLoginFailureShowsError` (data-driven, 4 invalid cases) |
| 3. Login success + homepage validation | `LoginTest.testLoginSuccessAndHomepageElements` |
| 4. Product navigation + validation | `ProductNavigationTest.java` |
| 5. Reusable custom commands | `ActionUtils.java` |
| 6. Page Object Model | `pages/` package |

## Prerequisites

- Java 17+ (`java -version`)
- Maven 3.8+ (`mvn -version`)
- Google Chrome installed (WebDriverManager auto-downloads the matching ChromeDriver — no manual driver setup needed)

## Running the tests

```bash
# From the project root:
mvn clean test
```

This runs `testng.xml`, which executes both test classes. A Chrome window will
open automatically for each test (comment/uncomment the `--headless=new`
option in `BaseTest.java` if you want it to run headless, e.g. in CI).

Test reports are generated under `target/surefire-reports/`.

## Test credentials used (public test-site accounts)

| Username | Password | Behavior |
|---|---|---|
| `standard_user` | `secret_sauce` | Valid login |
| `locked_out_user` | `secret_sauce` | Valid password, but account locked → error |
| `invalid_user` | `secret_sauce` | Invalid login |

## Extending this framework

- **Add a new page**: create a class in `pages/`, add locators as `By` fields,
  and expose actions as public methods (never expose raw `WebElement`s or
  `By` locators outside the page class).
- **Add a new reusable command**: add it to `ActionUtils.java` so every page
  object can reuse it.
- **Add a new test**: extend `BaseTest`, inject the relevant Page Object(s),
  and assert with TestNG's `Assert` class.

## Submission

Push this project to a Git repository (GitHub/GitLab/Bitbucket) and submit the
repo link, per the assignment's submission requirements.

```bash
git init
git add .
git commit -m "Assignment 7: Selenium + TestNG automation framework"
git branch -M main
git remote add origin <your-repo-url>
git push -u origin main
```
