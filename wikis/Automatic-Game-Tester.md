The automatic game tester's foundational technology is GitLab, specifically, the GitLab CI/CD function. The other core technology is Gradle.

IntelliJ gitlab-ci.yml is configured to use gradle:alpine, with 3 major sections: gradle-build, gradle-test, and rspec.  Gradle-build builds the project, gradle-test automatically runs the tests, and rspec generates the test result reports.

A runner needs to be installed and configured.  The runner sits on the developer's local machine, and is used to support the automation of the game tester.

The reports of the unit test runs can be found in the IDE, under the directory: gradle\build\test-results\test\

The other set of reports can be found in your gitlab-runner instance on your local machine, situated at:
C:\GitLab-Runner\builds\tgHUxQxs\0\u7305725\comp2120-2022-group-assignment-2\gradle\build\reports\tests\test\index.html

Potential improvements to this function are as follows:
> Link the XML report results to the GitLab CI/CD portal
> 