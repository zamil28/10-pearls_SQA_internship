# 🐞 Bug Reporting — Online Practice Websites

**QA Internship Assignment No. 3 — 10Pearls SHINE Internship Program**
**Author:** Syed Zamil Ali

---

## 📋 Project Overview

This repository contains the bug reporting documentation for a QA internship assignment focused on exploratory testing of publicly available practice/demo websites. The purpose of this project is to demonstrate the ability to identify functional, UI/UX, validation, and usability defects, document them using an industry-standard bug report format, assign severity and priority levels, and summarize findings in a set of release notes.

## 🎯 Learning Objectives

- Understand the bug identification process
- Practice identifying functional defects
- Practice identifying UI/UX defects
- Learn to write clear and structured bug reports
- Understand defect severity and priority
- Document bugs using industry-standard bug reporting formats

## 🗂️ Repository Contents

| File | Description |
|---|---|
| `Bug_Reports_Online_Practice_Sites.xlsx` | Full bug report workbook — includes a "Bug Reports" sheet (20 documented bugs) and a "Release Notes" sheet (auto-calculating summary statistics and quality observations) |

## 🌐 Sites Tested

- [buggy.justtestit.org](https://buggy.justtestit.org/) — Buggy Cars Rating
- [ParaBank](https://parabank.parasoft.com/parabank) — Parasoft demo banking app
- [OpenCart Demo](https://demo.opencart.com/)
- [OrangeHRM Demo](https://opensource-demo.orangehrmlive.com/)
- [Tutorials Ninja Demo](https://tutorialsninja.com/demo) (OpenCart mirror)

## 📖 Report Structure

### Bug Reports Sheet
20 bugs documented with the following required fields:
- Bug ID
- Title / Summary
- Description
- Steps to Reproduce
- Expected Result
- Actual Result
- Severity (Critical / High / Medium / Low)
- Priority (High / Medium / Low)
- Environment
- Status (Confirmed / Invalid / Blocked / Closed)
- Reported By
- Date Reported

### Release Notes Sheet
- Summary statistics (total bugs, severity breakdown, high-priority count) — live formulas, auto-update as rows change
- Major Functional Problems — the most significant confirmed defects
- Overall System Quality Observations — cross-site quality summary

## 🔍 Key Findings

| Bug ID | Site | Severity | Summary |
|---|---|---|---|
| BUG-011 | ParaBank | Critical | Account data accessible without authorization check — one user could view another user's account by editing the URL |
| BUG-012 | ParaBank | Critical | Registration form rejects every username as "already exists," blocking new sign-ups entirely |
| BUG-009 | OrangeHRM | Medium/High | PIM employee record count becomes incorrect after clearing search filters |
| BUG-010 | ParaBank | High | Fund transfer accepts negative amounts and same-account transfers with no validation |

## ✅ Task Checklist

- [x] Explore the provided buggy websites
- [x] Identify defects and unexpected behaviors
- [x] Document each bug using a standard bug report format
- [x] Assign severity and priority levels
- [x] Capture clear steps to reproduce
- [x] Provide expected and actual results
- [x] Prepare release notes summarizing the identified issues
- [x] Submit the Excel sheet through a public Git repository

## 📊 Execution Summary

| Metric | Result |
|---|---|
| Total Bugs Reported | 20 |
| Critical Severity | 2 |
| High Severity | 1 |
| Medium Severity | 5 |
| Low Severity | 3 |
| High Priority | 5 |

## 📥 How to View

Download and open [`Bug_Reports_Online_Practice_Sites.xlsx`](./Bug_Reports_Online_Practice_Sites.xlsx) in Microsoft Excel or any compatible spreadsheet application.

---

*Submitted as part of the 10Pearls SHINE Internship Program — QA Internship Bug Reporting Assignment.*
