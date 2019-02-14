package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class SELENIUM_scenario3 {
    private Click c ;
    private Function f;
    private WebDriver driver;

    @BeforeTest
    public void setUp(){

        driver=f.StartBrowser("http://automationpractice.com/index.php");
        f=new Function(driver);
        c=new Click(driver);
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }
    @Test
    public void Automat() throws InterruptedException {
        ArrayList<Double> productPrices=new ArrayList<Double>();
        c.MenuWomanClick();
        // blouse
        driver.findElement(By.id("layered_id_feature_5")).click();
        c.ClotheClick("Blouse");
        f.ChooseSize("M");
        productPrices.add(f.GetPrice());
        c.SubmitClick();
        c.ContinueShoppingClick();
        c.MenuWomanClick();
      //dress
        driver.findElement(By.id("layered_id_feature_20")).click();
        c.ClotheClick("Printed Chiffon Dress");
        f.ChooseSize("S");
        f.ChooseColor("Yellow");
        productPrices.add(f.GetPrice());
        c.SubmitClick();
        c.ProceedToCheckoutClick();
    //cart
        f.VerifyShoppingCart(productPrices);
    }
    @AfterTest
    public void TearDown(){
        //   driver.close();
    }
}
