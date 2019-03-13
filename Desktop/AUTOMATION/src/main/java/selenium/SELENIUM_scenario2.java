package selenium;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class SELENIUM_scenario2 {

    private WebDriver driver;
    private Function f;
    private Click c;
    @BeforeTest
    public void setUp(){

        driver=Function.StartBrowser("http://automationpractice.com/index.php");
        f=new Function(driver);
        c=new Click(driver);
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }
    @Test
    public void Automat() throws InterruptedException {
        c.LoginClick();
        f.LogIn("test@test.test","password");
       }
    @AfterTest
    public void TearDown(){
           driver.close();
    }
}
