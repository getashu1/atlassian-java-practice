# Atlassian-style Java repository practice: Inventory Service

This is a small, multi-file Java 21/Maven repository designed to practise repository exploration,
bug investigation, feature implementation, tests, and code review with GitHub Copilot in VS Code.

## Setup
Prerequisites: JDK 21 and Maven 3.9+.

```bash
mvn test
```

The initial test suite intentionally contains a failing regression test. Do not ask Copilot to
rewrite the project or reveal/fix everything immediately.

## Exercise 1 — interview brief (45–60 minutes)

A customer reports that reserving inventory sometimes increases the available stock instead of
reducing it. Investigate the existing flow, identify the root cause, and implement the smallest
safe fix.

Then implement this feature:

> Add `findLowStock(int threshold)` to `InventoryService`. It must return products whose available
> quantity is less than or equal to the threshold, sorted by SKU ascending. Reject a negative
> threshold with `IllegalArgumentException`. Do not expose or mutate the repository's internal map.

Requirements:
- Preserve the existing public API.
- Add focused JUnit 5 tests for the bug and feature.
- Cover zero, exact threshold, negative threshold, empty inventory, and ordering.
- Avoid unrelated refactoring.
- Run `mvn test` and inspect `git diff`.

## Rules for realistic practice
1. Start a timer for 60 minutes.
2. Use Copilot **Agent** mode, but first ask it to inspect and plan without edits.
3. Ask it to explain the relevant Java classes in Python-friendly language.
4. Review every proposed file change and terminal command.
5. Do not accept a fix you cannot explain.
6. At the end, explain the root cause, trade-offs, tests, and remaining risks aloud.

## Suggested first Copilot prompt

"Inspect this repository for the inventory reservation bug described in README.md. Do not edit files.
Trace the call flow, identify the exact faulty statement, explain the root cause, and propose the
smallest fix. Also explain the Java constructs in simple terms, assuming I know Python. Wait for
my approval before changing anything."
