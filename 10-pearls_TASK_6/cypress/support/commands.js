import LoginPage from "../pages/LoginPage";

/**
 * Custom Cypress commands (Task 5).
 * These wrap frequently repeated flows so spec files stay short and readable.
 * Registered globally via support/e2e.js.
 */

/** Logs in with the given credentials and does NOT assert anything (used for both success/failure specs). */
Cypress.Commands.add("login", (username, password) => {
  LoginPage.login(username, password);
});

/** Logs in with valid creds and waits until redirected to inventory page. Use this at the start of specs that need to already be logged in. */
Cypress.Commands.add("loginAsStandardUser", () => {
  cy.fixture("users").then((users) => {
    LoginPage.login(users.validUser.username, users.validUser.password);
  });
  cy.url().should("include", "/inventory.html");
});

/** Adds the nth product (0-indexed) on the inventory page to the cart directly from the listing. */
Cypress.Commands.add("addProductToCartByIndex", (index) => {
  cy.get("button[data-test^='add-to-cart']").eq(index).click();
});

/** Asserts the cart badge shows the expected item count. */
Cypress.Commands.add("assertCartCount", (expectedCount) => {
  if (expectedCount === 0) {
    cy.get(".shopping_cart_badge").should("not.exist");
  } else {
    cy.get(".shopping_cart_badge").should("have.text", String(expectedCount));
  }
});

/** Generic reusable wait-and-click helper for elements that need explicit visibility checks. */
Cypress.Commands.add("clickWhenVisible", (selector) => {
  cy.get(selector).should("be.visible").click();
});
