/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleniumtestproject;


import java.util.concurrent.TimeUnit;
import org.eclipse.jetty.util.log.Log;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTestProject {
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "/Users/andressolano/Desktop/Selenium/chromedriver/chromedriver");
        try {
        testTwo();
        } catch (Exception e) {
         e.printStackTrace();
        }
        
    }

    public static void testOne () throws InterruptedException {
        

        WebDriver driver = new ChromeDriver();

        driver.navigate().to("http://demo.nopcommerce.com");

        String pageTitle = driver.getTitle();
        
        try {
            Assert.assertEquals("nopCommerce demo store", pageTitle);
            System.out.println("El titulo es correcto: " + pageTitle);
        } catch (org.junit.ComparisonFailure e) {
            System.out.println("El titulo es incorrecto: " + pageTitle);
        }
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        Thread.sleep(500);

        
        driver.close();
        driver.quit();

    }
    
    
    public static void testTwo () throws InterruptedException {
        

        WebDriver driver = new ChromeDriver();
        String categoria = "home";
        try {
            
        driver.navigate().to("http://demo.nopcommerce.com");
        
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        categoria = "Computers";
        
        driver.navigate().to("http://demo.nopcommerce.com/computers");
        
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        
        categoria = "Electronics";
        
        driver.navigate().to("http://demo.nopcommerce.com/electronics");
        
        driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
        

        
        
            
        } catch (org.junit.ComparisonFailure e) {
            System.out.println("Error abriendo categoria: " + categoria);
        }
                
        

        
        driver.close();
        driver.quit();

    }
}
