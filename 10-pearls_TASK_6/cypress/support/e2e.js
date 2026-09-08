// This file runs before every single spec file.
// It's the ideal place to load global custom commands (Task 5).
import "./commands";

// Example of a global hook you could extend later, e.g. to suppress
// uncaught exceptions thrown by the app under test that aren't relevant
// to the assertions being made:
//
// Cypress.on("uncaught:exception", () => false);
