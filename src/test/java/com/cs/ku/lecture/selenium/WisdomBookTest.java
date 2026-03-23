package com.cs.ku.lecture.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.PullPolicy;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WisdomBookTest {

    private static WebDriver driver;

    private static final int WISDOM_BOOK_PORT = 8090;
    private static final GenericContainer<?> WISDOM_BOOK = new GenericContainer<>(DockerImageName.parse("ladyusa/wisdom-book:latest"))
            .withExposedPorts(8090)
            .waitingFor(Wait.forHttp("/")
                    .forPort(WISDOM_BOOK_PORT)
                    .forStatusCode(200)
            )
            .withImagePullPolicy(PullPolicy.defaultPolicy());

    @BeforeAll
    static void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WISDOM_BOOK.start();
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
        WISDOM_BOOK.stop();
    }

    @Test
    void testAccessWebsite() {
        // When
        driver.get("http://localhost:%s/".formatted(WISDOM_BOOK.getMappedPort(WISDOM_BOOK_PORT)));

        // Then
        assertEquals("Wisdom Book Website", driver.getTitle());

        WebElement heading = driver.findElement(By.tagName("h1"));
        assertEquals("Welcome to Wisdom Book Website", heading.getText());
    }

    @Test
    void testAddBook() {
        // Given
        var host = "http://localhost:%s".formatted(WISDOM_BOOK.getMappedPort(WISDOM_BOOK_PORT));
        driver.get(host + "/book/add");

        // When
        WebElement nameField = driver.findElement(By.id("nameInput"));
        WebElement authorField = driver.findElement(By.id("authorInput"));
        WebElement priceField = driver.findElement(By.id("priceInput"));
        nameField.sendKeys("Clean Code");
        authorField.sendKeys("Robert Martin");
        priceField.sendKeys("600");
        WebElement submitButton = driver.findElement(By.className("btn"));
        submitButton.click();
        WebElement name = driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]"));
        WebElement author = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]"));
        WebElement price = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]"));

        // Then
        assertEquals("Clean Code", name.getText());
        assertEquals("Robert Martin", author.getText());
        assertEquals("600.00", price.getText());
    }
}
