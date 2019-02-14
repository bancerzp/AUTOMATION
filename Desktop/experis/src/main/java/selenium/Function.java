package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class Function {
    private WebDriver driver;

    Function(WebDriver driver) {
        this.driver = driver;
    }

    protected void ChooseColor(String color) {
       driver.findElement(By.id("color_to_pick_list")).findElement(By.name(color)).click();
    }

    protected void ChooseSize(String size) {
        Select dropdown = new Select(driver.findElements(By.tagName("select")).get(0));
       dropdown.selectByVisibleText(size);
    }

    protected double GetPrice() {
        Double price = Double.parseDouble(driver.findElement(By.id("our_price_display")).getText().replace("$", ""));
        return price;
    }

    private double GetProductPrice(Integer productNumber){
        Double price = Double.parseDouble(driver.findElements(By.className("cart_total")).get(productNumber).getText().replace("$", ""));
        System.out.println(price);
        return price;
    }

    private  Double GetShippingPrice() {
        return Double.parseDouble(driver.findElement(By.id("total_shipping")).getText().replace("$", ""));
    }
    private  Double GetTotalPrice() {
        return Double.parseDouble(driver.findElement(By.id("total_price")).getText().replace("$", ""));
    }

    private Double GetTotalProduct() {
        return Double.parseDouble(driver.findElement(By.id("total_product")).getText().replace("$", ""));
    }

    protected void LogIn(String email, String password) {
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.id("SubmitLogin")).click();
    }

    protected void FillRegisterForm(Person person) {
        driver.findElement(By.id("uniform-id_gender" + person.gender.toString())).click();
        driver.findElement(By.id("customer_firstname")).sendKeys(person.firstName);
        driver.findElement(By.id("customer_lastname")).sendKeys(person.lastName);
        driver.findElement(By.id("passwd")).sendKeys(person.password);

        driver.findElement(By.id("days")).sendKeys(person.dof.split("-")[0]);
        Select dropdown = new Select(driver.findElement(By.id("months")));
        dropdown.selectByIndex(Integer.parseInt(person.dof.split("-")[1]));
        driver.findElement(By.id("years")).sendKeys(person.dof.split("-")[2]);

        if (driver.findElement(By.id("newsletter")).isSelected() != person.newsletter)
            driver.findElement(By.id("newsletter")).click();
        if (driver.findElement(By.id("optin")).isSelected() != person.offers) driver.findElement(By.id("optin")).click();
        driver.findElement(By.id("company")).sendKeys(person.company);
        driver.findElement(By.id("address1")).sendKeys(person.address);
        driver.findElement(By.id("address2")).sendKeys(person.address2);
        driver.findElement(By.id("city")).sendKeys(person.city);
        driver.findElement(By.id("id_state")).sendKeys(person.state);
        driver.findElement(By.id("postcode")).sendKeys(person.zip);
        driver.findElement(By.id("id_country")).sendKeys(person.country);
        driver.findElement(By.id("other")).sendKeys(person.addition);
        driver.findElement(By.id("phone")).sendKeys(person.homePhone);
        driver.findElement(By.id("phone_mobile")).sendKeys(person.mobilePhone);
        driver.findElement(By.id("alias")).sendKeys(person.addressAlias);
    }

    protected static WebDriver StartBrowser(String url) {
        System.getProperty("webdriver.gecko.driver", "..\\..\\..\\geckodriver.exe");
        FirefoxOptions foptions = new FirefoxOptions();
        foptions.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver();
        driver.get(url);
        return driver;
    }

    protected void TestEmail(String email, String expectedClass) {
        WebElement emailForm = driver.findElement(By.id("email_create"));
        emailForm.clear();
        emailForm.sendKeys(email);
        driver.findElement(By.id("create-account_form")).click();
        String getClass = emailForm.findElement(By.xpath("..")).getAttribute("class");
        Assert.assertEquals(getClass, expectedClass, "Class object: " + expectedClass);
    }

    protected void VerifyAddressData(Person person) {
        SoftAssert softAssert = new SoftAssert();
        String addresss=driver.findElement(By.xpath("//ul[@class='last_item item box']/li[5]")).getText();
        softAssert.assertTrue(addresss.startsWith(person.city) && addresss.endsWith(person.zip), "Address is OK");
        softAssert.assertEquals(driver.findElements(By.className("address_name")).get(1).getText(), person.company);
        softAssert.assertEquals(driver.findElement(By.className("address_company")).getText(), person.company);
        softAssert.assertEquals(driver.findElement(By.className("address_address1")).getText(), person.address);
        softAssert.assertEquals(driver.findElement(By.className("address_address2")).getText(), person.address2);
        softAssert.assertEquals(driver.findElement(By.xpath("//ul[@class='last_item item box']/li[6]")).getText(), person.country);
        softAssert.assertEquals(driver.findElement(By.className("address_phone")).getText(), person.homePhone);
        softAssert.assertEquals(driver.findElement(By.className("address_phone_mobile")).getText(), person.mobilePhone);
    }

    protected void VerifyPersonalInfo(Person person) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.findElement(By.id("uniform-id_gender" + person.gender.toString())).isSelected(), "Gender is correct");
        softAssert.assertEquals(driver.findElement(By.id("firstname")).getText(), person.firstName, "First name is correct");
        softAssert.assertEquals(driver.findElement(By.id("lastname")).getText(), person.lastName, "LastName id correct");
        softAssert.assertEquals(driver.findElement(By.id("email")).getText(), person.email, "Email is correct");
        softAssert.assertEquals(driver.findElement(By.id("days")).getText(), person.dof.split("-")[0], "Day dof is correct");
        softAssert.assertEquals(driver.findElement(By.id("years")).getText(), person.dof.split("-")[2], "Year dof is correct");
        softAssert.assertTrue(driver.findElement(By.id("newsletter")).isSelected() == person.newsletter, "Newsletter is correct");
        softAssert.assertTrue(driver.findElement(By.id("optin")).isSelected() == person.offers, "Offers are ok");
    }

    protected void VerifyShoppingCart(ArrayList<Double> productPrices){
        SoftAssert softAssert = new SoftAssert();
        Double shipPrice;
        Double totalPrice= Double.valueOf(0);
        for (int i=1;i<productPrices.size();i++){
            softAssert.assertEquals(productPrices.get(i-1),GetProductPrice(1),"Price product: "+i+" is correct");
            totalPrice+=productPrices.get(i-1);
        }

        shipPrice=GetShippingPrice();
        softAssert.assertEquals(totalPrice,GetTotalProduct(),"Product sum is correct");
        totalPrice+=shipPrice;
        softAssert.assertEquals(totalPrice,GetTotalPrice(),"Total price is correct");
    }


}
 class Person{
    Integer gender;
    String firstName;
    String lastName;
    String email;
    String password;
    String dof;
    boolean newsletter;
    boolean offers;
    String company;
    String address;
    String address2;
    String city;
    String state;
    String zip;
    String country;
    String addition;
    String homePhone;
    String mobilePhone;
    String addressAlias;

    public Person(Integer gender, String firstName, String lastName, String email, String password, String dof, boolean newsletter, boolean offers, String company, String address, String address2, String city, String state, String zip, String country, String addition, String homePhone, String mobilePhone, String addressAlias){
        this.gender=gender;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.password=password;
        this.dof=dof;
        this.newsletter=newsletter;
        this.offers=offers;
        this.company=company;
        this.address=address;
        this.address2=address2;
        this.city=city;
        this.state=state;
        this.zip=zip;
        this.country=country;
        this.addition=addition;
        this.homePhone=homePhone;
        this.mobilePhone=mobilePhone;
        this.addressAlias=addressAlias;
    }

}
