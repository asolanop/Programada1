/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleniumtestproject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTestProject {
    public static String HOME_PAGE = "http://demo.nopcommerce.com";
    public static String[] CATEGORIES = {"Computers", "Electronics", "Apparel", 
                        "Digital Downloads", "Books", "Jewelry", "Gift Cards"};
    public static String[] CATEGORIES_URLS = {"http://demo.nopcommerce.com/computers", 
                                "http://demo.nopcommerce.com/electronics", 
                                "http://demo.nopcommerce.com/apparel", 
                                "http://demo.nopcommerce.com/digital-downloads", 
                                "http://demo.nopcommerce.com/books", 
                                "http://demo.nopcommerce.com/jewelry", 
                                "http://demo.nopcommerce.com/gift-cards"};
    
    
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "/Users/andressolano/Desktop/Selenium/chromedriver/chromedriver");
        try {
        //testOne();
        testTwo();
        } catch (Exception e) {
         e.printStackTrace();
        }
        
    }

    public static void testOne () throws InterruptedException {
        

        WebDriver driver = new ChromeDriver();

        driver.navigate().to("Home");

        String pageTitle = driver.getTitle();
        
        try {
            Assert.assertEquals("nopCommerce demo store", pageTitle);
            System.out.println("El titulo es correcto: " + pageTitle);
        } catch (org.junit.ComparisonFailure e) {
            System.out.println("El titulo es incorrecto: " + pageTitle);
        }
                
        Thread.sleep(500);

        
        driver.close();
        driver.quit();

    }
    
    
    public static void testTwo () throws InterruptedException {
        

        WebDriver driver = new ChromeDriver();
        String category = "Home";
        
        ExpectedCondition<Boolean> pageLoad = (WebDriver driver1) -> 
                ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete");
        
        try {
            
        Wait wait = new WebDriverWait(driver, 10);
        
        driver.navigate().to(HOME_PAGE);
        wait.until(pageLoad);
        
        for(int i = 0; i < CATEGORIES.length; i++) {
            category = CATEGORIES[i];
            driver.navigate().to(CATEGORIES_URLS[i]);
            wait.until(pageLoad);
        }

        WebElement imageLink = driver.findElement(By.className("header-logo"));
        imageLink.click();
        
        System.out.println("Caso T2. Ejecutado correctamente");

            
        } catch (org.junit.ComparisonFailure e) {
            System.out.println("Error abriendo categoria: " + category);
        }
         
        driver.close();
        driver.quit();

    }
}
