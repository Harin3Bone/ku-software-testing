package com.cs.ku.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
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
    }
}
