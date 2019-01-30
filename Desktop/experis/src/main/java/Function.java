import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
/**
 * Created by patrycja on 30.01.2019.
 */
public class Function {
    WebDriver driver;
    public Function(WebDriver driver){
        this.driver=driver;
    }

    public void CreateAnAccountClick(){
        driver.findElement(By.id("SubmitCreate")).click();
    }

    public  void LoginClick(){
        driver.findElement(By.className("login")).click();
    }

    public void LogIn(String email, String password ){
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.id("SubmitLogin")).click();

    }

    public  void RegisterClick(){
        driver.findElement(By.id("submitAccount")).click();
    }

    public void TestEmail(String email, String expectedClass){
        WebElement emailForm= driver.findElement(By.id("email_create"));
        emailForm.clear();
        emailForm.sendKeys(email);
        driver.findElement(By.id("create-account_form")).click();
        String getClass=emailForm.findElement(By.xpath("..")).getAttribute("class");
        Assert.assertEquals(getClass,expectedClass,      "Class object: "+ expectedClass);
    }

    public static WebDriver StartBrowser(String url){
        System.getProperty("webdriver.gecko.driver", "..\\..\\..\\geckodriver.exe");
        FirefoxOptions foptions = new FirefoxOptions();
        foptions.setCapability("marionette",true);
        WebDriver driver=new FirefoxDriver();
        driver.get(url);
        return driver;
    }

    public void FillRegisterForm(Integer gender,String firstName,String lastName,String email,String password,String dof,boolean newsletter,boolean offers,String company,String address, String address2, String city,String state,String zip, String country,String addition,String homePhone,String mobilePhone,String addressAlias ){
    driver.findElement(By.id("uniform-id_gender"+gender.toString())).click();
    driver.findElement(By.id("customer_firstname")).sendKeys(firstName);
    driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
 //   driver.findElement(By.id("email")).sendKeys(email);
    driver.findElement(By.id("passwd")).sendKeys(password);
    driver.findElement(By.id("days")).sendKeys(dof.split("-")[0]);

        Select dropdown = new Select(driver.findElement(By.id("months")));

        dropdown.selectByIndex(Integer.parseInt(dof.split("-")[1]));
        // driver.findElement(By.id("months")).sendKeys();

    driver.findElement(By.id("years")).sendKeys(dof.split("-")[2]);

        if (driver.findElement(By.id("newsletter")).isSelected()!=newsletter)   driver.findElement(By.id("newsletter")).click();
    if (driver.findElement(By.id("optin")).isSelected()!=offers)   driver.findElement(By.id("optin")).click();

 //   driver.findElement(By.id("firstName")).sendKeys(firstName);
  //  driver.findElement(By.id("lastName")).sendKeys(lastName);
    driver.findElement(By.id("company")).sendKeys(company);
    driver.findElement(By.id("address1")).sendKeys(address);
    driver.findElement(By.id("address2")).sendKeys(address2);
    driver.findElement(By.id("city")).sendKeys(city);
    driver.findElement(By.id("id_state")).sendKeys(state);
    driver.findElement(By.id("postcode")).sendKeys(zip);
    driver.findElement(By.id("id_country")).sendKeys(country);
    driver.findElement(By.id("other")).sendKeys(addition);
    driver.findElement(By.id("phone")).sendKeys(homePhone);
    driver.findElement(By.id("phone_mobile")).sendKeys(mobilePhone);
    driver.findElement(By.id("alias")).sendKeys(addressAlias);
    }
}
