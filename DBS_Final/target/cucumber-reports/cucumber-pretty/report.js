$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("awardlist.feature");
formatter.feature({
  "line": 1,
  "name": "Awardlist",
  "description": "",
  "id": "awardlist",
  "keyword": "Feature"
});
formatter.before({
  "duration": 308800,
  "status": "passed"
});
formatter.scenario({
  "line": 2,
  "name": "User validates the award list of DBS",
  "description": "",
  "id": "awardlist;user-validates-the-award-list-of-dbs",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 3,
  "name": "user opens the DBS URL",
  "keyword": "Given "
});
formatter.step({
  "line": 4,
  "name": "user clicks on Learn More button",
  "keyword": "When "
});
formatter.match({
  "location": "StepDefinitions.UserOpensTheDBSURL()"
});
formatter.result({
  "duration": 13791135800,
  "status": "passed"
});
formatter.match({
  "location": "StepDefinitions.userClicksOnLearnMoreButton()"
});
formatter.result({
  "duration": 2301500,
  "error_message": "java.lang.NullPointerException\r\n\tat pages.Pageawardlist.clickbtn(Pageawardlist.java:23)\r\n\tat stepdefs.StepDefinitions.userClicksOnLearnMoreButton(StepDefinitions.java:23)\r\n\tat ✽.When user clicks on Learn More button(awardlist.feature:4)\r\n",
  "status": "failed"
});
});