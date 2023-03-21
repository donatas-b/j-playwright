# j-playwright

This is an example of using Playwright for web UI test automation. The tests are utilizing [XYZ Bank](https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login) application.

Project is using Java with Playwright, Cucumber, JUnit and Allure reports. 

Project architecture was somewhat influenced by Screenplay pattern (page objects provide only UI elements, Tasks are used to perform actions and get resuts from UI).

## Executing the tests
To run the sample project, you can either just run the `Tests` test runner class, use maven from the command line or run feature/ scenario individually from e.g. IntelliJ.

By default, the tests will run using Chrome.
```
$ mvn clean verify
```
Test report will be generated under `allure-results`, it can be viewed by running `allure serve` from project root folder. 

## Useful links

### Web pages
https://playwright.dev/java/docs/intro

https://thats-it-code.com/categories/playwright/

https://qaautomationlabs.com/page-object-model-with-playwright-with-java/

https://medium.com/@sonaldwivedi/page-object-model-with-playwright-java-c05c67cc3081

### GitHub repositories
https://github.com/sonaldwivedi/Playwright

