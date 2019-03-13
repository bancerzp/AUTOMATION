Feature: scanario1
  Scenario:
    Given I have 200PLN on my bank account

    When I want to withdraw 500PLN

    Then Check if error appears

    And Check how much money did I withdraw

    And Check my account balance





Feature: scanario2
  Scenario:
    Given I have 200PLN on my bank account

    When I want to withdraw 0/50/100/150/200PLN

    Then Check if i get appropriate amount

    And Check if my account balance after withdraw is correct