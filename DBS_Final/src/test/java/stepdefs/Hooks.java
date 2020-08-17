package stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {
WebDriver driver;
@Before (order=0)
    public void before(Scenario scenario)
{
    scenario.getName();
}

@After (order=0)
   public void afterscenario(Scenario scenario)
{
    String status= scenario.getStatus();
    if(status=="FAILED")
    {
        TakesScreenshot scr= ((TakesScreenshot)driver);
        final byte[] screenshot = scr.getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot,"image/png");
    }



}
@After(order=1)
    public void teardown()
{
    driver.quit();
}
}
