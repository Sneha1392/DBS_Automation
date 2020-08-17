Feature: Awardlist
  Scenario: User validates the award list of DBS
    Given user opens the DBS URL
    When user clicks on Learn More button
    And user scrolls down to select the Country
    Then verify table is copied to excel
    Then user clicks on About from menu
    And user clicks on Who we are from submenu
    And user validates awards
    #And user verifies table with award names created in report


