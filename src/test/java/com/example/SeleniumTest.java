package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class SeleniumTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/chemin/vers/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testAddTask() {
        driver.get("http://localhost:8080");

        WebElement inputField = driver.findElement(By.id("taskInput"));
        inputField.sendKeys("Ma première tâche");
        WebElement addButton = driver.findElement(By.id("addTaskButton"));
        addButton.click();

        WebElement taskList = driver.findElement(By.id("taskList"));
        assertTrue(taskList.getText().contains("Ma première tâche"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
