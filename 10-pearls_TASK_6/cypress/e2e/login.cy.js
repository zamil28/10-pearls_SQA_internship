import LoginPage from "../pages/LoginPage";
import InventoryPage from "../pages/InventoryPage";

describe("Login", () => {
  beforeEach(() => {
    cy.fixture("users").as("users");
  });

  // ---------------------------------------------------------------------
  // Task 2: Login failure scenarios
  // ---------------------------------------------------------------------
  context("Login failure scenarios", () => {
    it("shows an error for an incorrect password", function () {
      const { username, password } = this.users.wrongPasswordUser;
      LoginPage.login(username, password);

      LoginPage.getErrorMessage()
        .should("be.visible")
        .and("contain.text", "Username and password do not match");

      cy.url().should("not.include", "inventory.html");
    });

    it("shows an error for an invalid/unknown username", function () {
      const { username, password } = this.users.invalidUser;
      LoginPage.login(username, password);

      LoginPage.getErrorMessage()
        .should("be.visible")
        .and("contain.text", "Username and password do not match");
    });

    it("shows an error when fields are left blank", function () {
      const { username, password } = this.users.blankUser;
      LoginPage.login(username, password);

      LoginPage.getErrorMessage()
        .should("be.visible")
        .and("contain.text", "Username is required");
    });

    it("shows an error for a locked-out account", function () {
      const { username, password } = this.users.lockedOutUser;
      LoginPage.login(username, password);

      LoginPage.getErrorMessage()
        .should("be.visible")
        .and("contain.text", "Sorry, this user has been locked out");
    });
  });

  // ---------------------------------------------------------------------
  // Task 3: Login success flow + homepage validation
  // ---------------------------------------------------------------------
  context("Login success flow", () => {
    it("logs in with valid credentials and lands on the homepage", function () {
      const { username, password } = this.users.validUser;
      LoginPage.login(username, password);

      cy.url().should("include", "/inventory.html");
      InventoryPage.getPageTitle().should("have.text", "Products");
    });

    it("shows key homepage/navigation elements after login", function () {
      const { username, password } = this.users.validUser;
      LoginPage.login(username, password);

      InventoryPage.isNavMenuVisible().should("be.visible");
      InventoryPage.isCartIconVisible().should("be.visible");
      InventoryPage.getProductCount().should("have.length.greaterThan", 0);
    });

    it("supports the custom loginAsStandardUser command for reuse in other specs", () => {
      cy.loginAsStandardUser();
      InventoryPage.getPageTitle().should("have.text", "Products");
    });
  });
});
