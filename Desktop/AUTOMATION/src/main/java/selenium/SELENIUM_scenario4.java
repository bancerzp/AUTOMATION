package selenium;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class SELENIUM_scenario4 {
    private Click c ;
    private Function f;
    private WebDriver driver;
    static Logger logger = Logger.getLogger(SELENIUM_scenario4.class);

    @BeforeTest
    public void setUp(){
        String log4jConfPath = "C:\\Users\\patrycja\\Desktop\\AUTOMATION\\src\\main\\java\\selenium\\log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hhmmss");
        System.setProperty("current.date", dateFormat.format(new Date()));


        driver=f.StartBrowser("http://automationpractice.com/index.php");
        f=new Function(driver);
        c=new Click(driver);
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);

        logger.debug("This is debug message");

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
