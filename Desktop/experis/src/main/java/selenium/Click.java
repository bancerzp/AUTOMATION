package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Click {
    WebDriver driver;
    public Click(WebDriver driver){
        this.driver=driver;
    }

    public void ClotheClick(String name){
        driver.findElement(By.xpath("//img[@title='"+name+"']")).click();
    }

    public void ContinueShoppingClick() throws InterruptedException {
        Thread.sleep((2000));
        driver.findElement(By.xpath("//span[@title='Continue shopping']/span/i")).click();
    }

    public void CreateAnAccountClick(){
        driver.findElement(By.id("SubmitCreate")).click();
    }

    public  void LoginClick(){
        driver.findElement(By.className("login")).click();
    }

    public void MenuWomanClick(){
        driver.findElement(By.xpath("//a[@title='Women']")).click();
    }

    public void MyPersonalInfoClick(){
        driver.navigate().to("http://automationpractice.com/index.php?controller=identity");
    }

    public void MyAddressDataClick(){
        driver.navigate().to("http://automationpractice.com/index.php?controller=addresses");
    }

    public void ProceedToCheckoutClick(){
        driver.findElement(By.partialLinkText("Proceed to checkout")).click();
    }

    public  void RegisterClick(){
        driver.findElement(By.id("submitAccount")).click();
    }

    public void SubmitClick() {
        driver.findElement(By.name("Submit")).click();
    }
}
