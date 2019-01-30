import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class scenario1 {

    WebDriver driver;
    Function f;
    @BeforeTest
    public void setUp(){

        driver=Function.StartBrowser("http://automationpractice.com/index.php");
        f=new Function(driver);
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }
    @Test
    public void Automat() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
       // driver.findElement(By.className("login")).click();
        f.LoginClick();
        f.TestEmail("err","form-group form-error");
        f.CreateAnAccountClick();
        String bodyText = driver.findElement(By.tagName("body")).getText();
        softAssert.assertTrue(bodyText.contains("Invalid email address."),"Invalid email address.");
        f.TestEmail("test@test.testtt","form-group form-ok");
        f.CreateAnAccountClick();

        f.FillRegisterForm(1,"Janusz","Miałczyński","test@test.testtt","password","20-11-1987",true,true,"company","add1","add2","Warsaw","Idaho","21098","United States","","123-123-123","123-123-123","alias");
        f.RegisterClick();
        Thread.sleep(3000);
        softAssert.assertTrue(driver.getCurrentUrl()=="http://automationpractice.com/index.php?controller=my-account","Account has created");
    }
    @AfterTest
    public void TearDown(){
        //   driver.close();
    }
}
