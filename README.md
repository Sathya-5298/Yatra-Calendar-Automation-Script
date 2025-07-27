# ✈️ Yatra.com Price Comparison Automation Script

## Overview

This Selenium-based Java automation script intelligently navigates the flight booking interface of [Yatra.com](https://www.yatra.com), extracts departure dates and corresponding ticket prices, and identifies the lowest available fares across two consecutive months. It demonstrates strategic use of dynamic locators, WebDriver synchronization, and clean parsing logic—hallmarks of resilient and production-grade automation.

## 🔍 Key Features

- **Popup Handling:** Detects and dismisses modal dialogs using WebDriverWait and `ExpectedConditions`.
- **Calendar Date Interaction:** Selects calendar month containers dynamically with support for future scalability.
- **Dynamic Price Extraction:** Retrieves and parses ticket prices via context-aware XPath expressions.
- **Custom Comparisons:** Compares lowest available fares across months with logical inference and precise string handling.

## 🧪 Technologies & Tools Used

| Technology  | Purpose                         |
|-------------|----------------------------------|
| Java        | Core programming language        |
| Selenium WebDriver | Browser automation          |
| WebDriverWait | Synchronization and resilience |
| XPath       | DOM traversal and dynamic locators |
| ChromeOptions | Notification suppression         |

## 🧠 Key Learnings and Technical Insights
- Developed a strong command of context-aware XPath crafting to precisely interact with dynamic web elements.
- Strengthened expertise in WebDriver synchronization using explicit waits for reliable automation flows.
- Applied robust exception handling to gracefully manage unexpected UI behavior like popups or delayed elements.
- Sharpened skills in data extraction and parsing, especially when converting unstructured UI text into meaningful metrics.
- Practiced clean modular coding techniques, ensuring maintainability, readability, and ease of debugging across multiple methods.

## 🚀 How to Run

1. Install the latest [ChromeDriver](https://chromedriver.chromium.org/downloads) compatible with your Chrome browser.
2. Add Selenium libraries to your Java project.
3. Clone this repository or paste the source into your IDE.
4. Run `YatraAutomationScript.java` as a standalone Java application.
5. Observe the console output to view lowest price comparisons.

## 🛠️ Troubleshooting

If you encounter a `NumberFormatException`, ensure price strings are cleaned using:

```java
priceValue = priceValue.replace("₹", "").replace(",", "");
