package com.escanan.ealden.race.page;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.CdpVersionFinder;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.ProtocolHandshake;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.OFF;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public abstract class BasePage {
    protected final WebDriver driver;

    private final By testProcessing = By.id("test-processing");

    public BasePage(boolean headless) {
        reallySilenceSelenium();

        driver = createDriver(headless);
    }

    private void reallySilenceSelenium() {
        Logger.getLogger(ProtocolHandshake.class.getName()).setLevel(OFF);
        Logger.getLogger(CdpVersionFinder.class.getName()).setLevel(OFF);
    }

    private WebDriver createDriver(boolean headless) {
        WebDriverManager.edgedriver().setup();

        EdgeOptions options = new EdgeOptions();
        options.setHeadless(headless);

        return new EdgeDriver(options);
    }

    public void close() {
        driver.quit();
    }

    protected void navigateTo(String url) {
        driver.navigate().to(url);
    }

    protected void click(By element) {
        findElement(element).click();
    }

    protected void input(By element, int i) {
        input(element, "" + i);
    }

    protected void input(By element, String keys) {
        findElement(element).clear();
        findElement(element).sendKeys(keys);
    }

    protected void waitUntilProcessingComplete() {
        doWait().until(invisibilityOfElementLocated(testProcessing));
    }

    private WebDriverWait doWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    protected WebElement findElement(By by) {
        return doWait().until(visibilityOfElementLocated(by));
    }

    protected List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    protected WebElement findTestElement(Long id, String key) {
        String testId = String.join("-", "test", "racer", id.toString(), key);

        return findElement(By.id(testId));
    }
}
