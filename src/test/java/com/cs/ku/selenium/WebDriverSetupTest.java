package com.cs.ku.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WebDriverSetupTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        driver = new ChromeDriver();
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @Test
    void testBaeldung() {
        // When
        driver.get("https://www.baeldung.com/maven-plugin");

        // Then
        assertNotNull(driver.getTitle());
        assertEquals("How to Create a Maven Plugin | Baeldung", driver.getTitle());

        driver.findElement(By.tagName("body"));
    }

    @Test
    void testChromeBrowser() {
        // Given
        var chromeDriver = new ChromeDriver();

        // When
        chromeDriver.get("https://www.google.com");

        // Then
        assertTrue(Objects.requireNonNull(chromeDriver.getTitle()).startsWith("Google"));

        // Tear Down
        chromeDriver.quit();
    }

    @Test
    void testEdgeBrowser() {
        // Given
        var edgeDriver = new EdgeDriver();

        // When
        edgeDriver.get("https://www.google.com");

        // Then
        assertTrue(Objects.requireNonNull(edgeDriver.getTitle()).startsWith("Google"));

        // Tear Down
        edgeDriver.quit();
    }

}
