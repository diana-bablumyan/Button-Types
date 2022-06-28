package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;


public class DiffType {
    public static WebDriver driver;

    @BeforeClass
    public static void initWebDriver() {
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://courses.letskodeit.com/practice");
    }

    @Test
    public void radioBtn(){
        WebElement bmwRadioBtn = driver.findElement(By.id("bmwradio"));
        bmwRadioBtn.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebElement benzRadioBtn = driver.findElement(By.id("benzradio"));
        benzRadioBtn.click();

        Assert.assertFalse(bmwRadioBtn.isSelected(),"MBW still selected");
        Assert.assertTrue(benzRadioBtn.isSelected(), "Benz should be selected");

    }

    @Test
    public void checkboxBtn(){
        WebElement bmwCheckBtn = driver.findElement(By.id("bmwcheck"));
        bmwCheckBtn.click();

        WebElement benzCheckBtn = driver.findElement(By.id("benzcheck"));
        benzCheckBtn.click();

        Assert.assertTrue(bmwCheckBtn.isSelected(),"BMW should be selected");
        Assert.assertTrue(benzCheckBtn.isSelected(), "Benz should be selected");
    }

    @Test
    public void dropdownBtn() {
        WebElement dropdown = driver.findElement(By.id("carselect"));
        Select dropwdownElement = new Select(dropdown);
        dropwdownElement.selectByVisibleText("Benz");
        List<WebElement> selected = dropwdownElement.getAllSelectedOptions();
        List<String> selectedString = selected.stream().map(e->e.getText()).collect(Collectors.toList());
        Assert.assertEquals(selectedString.size(), 1, "Incorrect number of selections");
        Assert.assertTrue(selectedString.contains("Benz"), "Option not selected");

    }

    @AfterClass
    public static void afterclass() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.quit();
    }
}