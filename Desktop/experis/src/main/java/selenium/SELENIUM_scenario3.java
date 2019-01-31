package selenium;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

/**
 * Created by patrycja on 30.01.2019.
 */
public class SELENIUM_scenario3 {
    Click c ;
    Function f;
    WebDriver driver;

    @BeforeTest
    public void setUp(){

        driver=Function.StartBrowser("http://automationpractice.com/index.php");
        f=new Function(driver);
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }
    @Test
    public void Automat() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        c.MenuWomanClick();


    }
    @AfterTest
    public void TearDown(){
        //   driver.close();
    }
}
