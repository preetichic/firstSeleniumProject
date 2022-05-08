package seleniumHomeworkTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;
import java.util.List;

public class NextCountrySelector {

    /* 1. Find a Browser.
     * 2. Open Browser.
     * 3. Enter the site URL.
     * 4. Identify the country flag icon from UI.
     * 5. Click on the country flag icon.
     * 6. From the countries drop down list choose "NZ".
     * 7.Click on shop now button.
     * 8. Redirects to domain New Zealand home page.
     * 9. Ask selenium to verify It's landed on right page.
     * 10. Close browser* */
    public WebDriver driver;

    @Before
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window();
        driver.get("https://www.next.co.uk");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement cookieButton = driver.findElement(By.id("onetrust-accept-btn-handler"));
        cookieButton.click();
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void selectCountry() throws InterruptedException {


        this.driver.findElement(By.xpath("//*[@id='platform_modernisation_header']/header/div[1]/nav/div[8]/div/button")).click();
        this.driver.findElement(By.id("mui-component-select-country-selector-select")).click();
                String expectedCountryToSelect = "NZ";
        List<WebElement> locatedCountries = driver.findElements(By.xpath("//input[@id='country-selector-select']/following-sibling::div/div[3]/ul/li/div/img"));

        String currentCountrySelect;
        for (WebElement country: locatedCountries) {
            currentCountrySelect = country.getAttribute("alt");
            if (currentCountrySelect.equals(expectedCountryToSelect)) {
                country.click();
                break;
            }
        }

        this.driver.findElement(By.xpath("//span[text()='SHOP NOW']")).click();
        String actualURL = this.driver.getCurrentUrl();
        String requiredURL = this.driver.getCurrentUrl();
        Assert.assertEquals(requiredURL, actualURL);
        Thread.sleep(5000L);
        currentCountrySelect = this.driver.getTitle();
        System.out.println("ET:" + currentCountrySelect);
        String actualTitle = "Next New Zealand | Shop Online For Fashion & Clothing";
        Assert.assertEquals(currentCountrySelect, actualTitle);
    }
}
