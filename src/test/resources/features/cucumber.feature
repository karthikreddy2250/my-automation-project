@smoke
Feature: This is a sample test

  Scenario Outline: Sample Test
    When I open the url
    And validate the welcome text '<welcomeText>'
    And enter the basic auth credentials '<username>' and '<password>'
    Then validate the success message '<successMessage>'
    Then click on back
    Then click on Broken Images link
    Then check if the images loaded or not
    Then click on back
    Then click on Challenging DOM
    Then validate the table header
    Then validate the table rows
    Then click on back
    Then click on Checkboxes link
    Then check a box

    Examples:
      | username | password | welcomeText             | successMessage                                         |
      | admin    | admin    | Welcome to the-internet | Congratulations! You must have the proper credentials. |