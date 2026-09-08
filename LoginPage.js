/**
 * Page Object for the SauceDemo login page.
 * Holds ONLY locators + actions — no assertions here (Task 6: POM).
 * Assertions live in the spec files.
 */
class LoginPage {
  // ---- Locators ----
  elements = {
    usernameInput: () => cy.get("#user-name"),
    passwordInput: () => cy.get("#password"),
    loginButton: () => cy.get("#login-button"),
    errorMessage: () => cy.get("h3[data-test='error']"),
    errorButton: () => cy.get(".error-button"),
  };

  // ---- Actions ----
  visit() {
    cy.visit("/");
    return this;
  }

  enterUsername(username) {
    this.elements.usernameInput().clear().type(username);
    return this;
  }

  enterPassword(password) {
    this.elements.passwordInput().clear().type(password, { log: false });
    return this;
  }

  clickLogin() {
    this.elements.loginButton().click();
    return this;
  }

  /** Full login flow in one call. */
  login(username, password) {
    this.visit();
    this.enterUsername(username);
    this.enterPassword(password);
    this.clickLogin();
    return this;
  }

  getErrorMessage() {
    return this.elements.errorMessage();
  }
}

export default new LoginPage();
