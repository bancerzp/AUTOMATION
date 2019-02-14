package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class SELENIUM_scenario1 {

    private WebDriver driver;
    private Click c;
    private Function f;
    @BeforeTest
    public void setUp(){
        driver=Function.StartBrowser("http://automationpractice.com/index.php");
        f=new Function(driver);
        c=new Click(driver);
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }
    @Test
    public void Automat() throws InterruptedException {
        String email="test9@test.test";
        Person person=new Person(1,"Janusz","Miałczyński",email,"password","20-11-1987",true,true,"company","add1","add2","Warsaw","Idaho","21098","United States","","123-123-123","123-123-123","alias");
        SoftAssert softAssert = new SoftAssert();

        c.LoginClick();
        f.TestEmail("err","form-group form-error");
        c.CreateAnAccountClick();
        String bodyText = driver.findElement(By.tagName("body")).getText();
        softAssert.assertTrue(bodyText.contains("Invalid email address."),"Invalid email address.");
        f.TestEmail(email,"form-group form-ok");
        c.CreateAnAccountClick();

        f.FillRegisterForm(person);
        c.RegisterClick();
        Thread.sleep(3000);
        softAssert.assertTrue(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=my-account"),"Account has created");

        c.MyPersonalInfoClick();
        f.VerifyPersonalInfo(person);
        c.MyAddressDataClick();
        f.VerifyAddressData(person);
    }
    @AfterTest
    public void TearDown(){
           driver.close();
    }
}
