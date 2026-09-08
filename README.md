# Assignment 6 – Cypress Automation Framework

End-to-end UI automation for the [SauceDemo](https://www.saucedemo.com) web
application, built with **Cypress**, following the **Page Object Model (POM)**
and using **custom reusable commands**.

## Project Structure

```
cypress-framework/
├── package.json
├── cypress.config.js                 # Cypress config (Task 1)
├── cypress/
│   ├── e2e/
│   │   ├── login.cy.js               # Task 2 + Task 3
│   │   └── productNavigation.cy.js   # Task 4
│   ├── pages/                        # Page Object Model classes (Task 6)
│   │   ├── LoginPage.js
│   │   ├── InventoryPage.js
│   │   └── ProductPage.js
│   ├── support/
│   │   ├── commands.js               # Custom reusable commands (Task 5)
│   │   └── e2e.js                    # Loads commands.js before every spec
│   └── fixtures/
│       └── users.json                # Test credentials used across specs
└── .gitignore
```

## How each task maps to the code

| Task | Where it lives |
|---|---|
| 1. Setup Cypress project | `package.json`, `cypress.config.js` |
| 2. Login failure scenarios | `login.cy.js` → `"Login failure scenarios"` block (4 cases) |
| 3. Login success + homepage validation | `login.cy.js` → `"Login success flow"` block |
| 4. Product navigation + validation | `productNavigation.cy.js` |
| 5. Reusable custom commands | `support/commands.js` (`cy.login`, `cy.loginAsStandardUser`, `cy.addProductToCartByIndex`, `cy.assertCartCount`, `cy.clickWhenVisible`) |
| 6. Page Object Model | `cypress/pages/` (`LoginPage`, `InventoryPage`, `ProductPage`) |

## Prerequisites

- [Node.js](https://nodejs.org/) v16+ installed (`node -v`)
- npm (comes with Node)

## Setup

```bash
git clone <this-repo-url>
cd cypress-framework
npm install
```

`npm install` downloads Cypress itself (including its binary), so this step
needs internet access and may take a minute the first time.

## Running the tests

**Interactive mode** (opens the Cypress Test Runner UI — good for
development/debugging):
```bash
npm run cy:open
```

**Headless mode** (runs all specs in the terminal — good for CI):
```bash
npm run cy:run
```

## Test credentials used (public SauceDemo test accounts)

| Username | Password | Behavior |
|---|---|---|
| `standard_user` | `secret_sauce` | Valid login |
| `locked_out_user` | `secret_sauce` | Valid password, but account locked → error |
| `invalid_user` | `secret_sauce` | Invalid login |
| `standard_user` | `wrong_password` | Wrong password |

All of these live in `cypress/fixtures/users.json` rather than hardcoded in
the specs, so credentials can be updated in one place.

## Extending this framework

- **Add a new page**: create a class in `cypress/pages/`, define locators
  under an `elements` object, and expose actions as methods that return
  `this` for chaining. Export a singleton instance (`export default new
  MyPage();`).
- **Add a new reusable command**: add it to `cypress/support/commands.js` via
  `Cypress.Commands.add(...)` so any spec can call `cy.myCommand(...)`.
- **Add a new spec**: create a `*.cy.js` file under `cypress/e2e/`, import the
  relevant Page Object(s), and write assertions with Cypress's built-in
  `should`/`expect` syntax.

## Submission

Push this project to a **public** GitHub repository and submit the repo URL.

```bash
git init
git add .
git commit -m "Assignment 6: Cypress automation framework"
git branch -M main
git remote add origin <your-repo-url>
git push -u origin main
```
