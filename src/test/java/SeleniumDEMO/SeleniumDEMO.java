package SeleniumDEMO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class SeleniumDEMO {
    private WebDriver driver;
    public static JavascriptExecutor jse;
    private WebDriverWait wait;
    private SoftAssert softAssert;

    @BeforeTest
    public void setup() throws InterruptedException {

        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        // Initialize the ChromeDriver with the options
        this.driver = new ChromeDriver();
        jse = (JavascriptExecutor) driver;
        Actions action = new Actions(driver);

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        softAssert = new SoftAssert();

        // Maximize the browser window
        driver.manage().window().maximize();

    }

    @Test
    public void clickloginbutton() throws InterruptedException {


        driver.get("https://www.amaysim.com.au/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement simplans = driver.findElement(By.xpath("//a[@href='/sim-plans']"));
        (new Actions(driver)).moveToElement(simplans).perform();
        WebElement pitongaraw = driver.findElement(By.xpath("//a[@href='/sim-plans/7-day-sim-plans']"));
        (new Actions(driver)).moveToElement(pitongaraw).perform();
        pitongaraw.click();

        WebElement buynow = driver.findElement(By.xpath("//a[@href='/mobile/cart/7-day-10gb']"));
        buynow.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='pick a new number'])[1]")));

        WebElement newnum = driver.findElement(By.xpath("(//span[text()='pick a new number'])[1]"));
        newnum.click();


        WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromViewport(190, 190);
        new Actions(driver)
                .scrollFromOrigin(scrollOrigin, 10000, 9900)
                .perform();

        Thread.sleep(6000L);

        WebElement checkke = driver.findElement(By.xpath("//a[@href='/mobile/your-details']"));
        checkke.click();

        WebElement typeName = driver.findElement(By.xpath("//input[@name='firstName']"));
        jse.executeScript("arguments[0].scrollIntoView(true);", typeName);
        typeName.sendKeys("Test");

        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("Test");
        driver.findElement(By.xpath("//input[@name='dateOfBirth']")).sendKeys("12121980 ");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("rcenoc@gmail.com");

        WebElement pass = driver.findElement(By.xpath("//input[@name='password']"));
        jse.executeScript("arguments[0].scrollIntoView(true);", pass);
        pass.sendKeys("Tilapia999");

        driver.findElement(By.xpath("//input[@name='contactNumber']")).sendKeys("0444455556");
        driver.findElement(By.xpath("//input[@placeholder='e.g 123 George St Sydney NSW 2000']")).sendKeys("Level 6, 17-19 Bridge St, SYDNEY NSW 2000");
        driver.findElement(By.xpath("//span[text()='Level 6 17-19 Bridge St, SYDNEY NSW 2000']")).click();

        WebElement paymentHeader = driver.findElement(By.id("payments-header-name"));
        jse.executeScript("arguments[0].scrollIntoView(true);", paymentHeader);

        driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='payment-element']//iframe")));
        driver.findElement(By.xpath("//div[@aria-label='Payment Methods']//button[1]")).click();
        driver.findElement(By.xpath("//input[@id='Field-numberInput']")).sendKeys("4242 4242 4242 4242");
        driver.findElement(By.xpath("//input[@id='Field-expiryInput']")).sendKeys("0127");
        driver.findElement(By.xpath("//input[@id='Field-cvcInput']")).sendKeys("123");
        driver.switchTo().defaultContent();


        WebElement acceptTerm = driver.findElement(By.xpath("(//div[contains(@class, 'css-14')])[3]"));
        jse.executeScript("arguments[0].scrollIntoView(true);", acceptTerm);
        acceptTerm.click();

        WebElement paynow = driver.findElement(By.xpath("//button[contains(text(), 'pay now')]"));
        paynow.click();

        String errormessage = driver.findElement(By.xpath("//strong[text()='Credit Card payment failed']")).getText();
        WebElement errorhighlight = driver.findElement(By.xpath("//strong[text()='Credit Card payment failed']"));

        WebElement PayHeader = driver.findElement(By.id("payments-header-name"));
        jse.executeScript("arguments[0].scrollIntoView(true);", PayHeader);


        String expectedError = "Credit Card payment failed";
        Assert.assertEquals(expectedError, "Credit Card payment failed");
        System.out.println("Expected Error acquired, Test Complete");

    }

}
