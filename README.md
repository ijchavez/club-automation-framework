# Club Automation

This is a java project using Selenium and RestAssured to ensure correct behavior 

## Table of Contents

- [Getting Started](#getting-started)
- [Usage](#usage)
- [Dependencies](#dependencies)
- [Contributing](#contributing)

## Getting Started

To compile and run this project, follow these steps
1. Make sure you have Java Development Kit (JDK) installed in your system (20).
2. Navigate to the root directory of the project via (terminal or IDe).
3. From the IDE, you can run hits the triangle button.
   3.1 To run in headless mode the options are sets if not, you need to change this configuration in the helperMethod class,also need to change the RunTests with the tag what you want to execute.
4. From the terminal you can run with a maven command (ENVIRONMENTS=QA mvn clean verify test -Dcucumber.options="--tags @Login,@CategoryUI") if you want to execute only these tags, otherwise you can run the tag @Regression ENVIRONMENTS=QA mvn clean verify test -Dcucumber.options="--tags @Regression")
5. The Password, the environment and the Captcha token should be passed as environment variables (CAPTCHA_LOCAL_STORAGE_VALUE;ENVIRONMENTS=QA;PASSWORD=;PASSWORD_SUPERADMIN=)
## Usage

Provide examples and explanations of how to use your Java project.

## Dependencies



## Contributing

Contributions are welcome! If you find any issues or want to suggest improvements, please create a pull request.
