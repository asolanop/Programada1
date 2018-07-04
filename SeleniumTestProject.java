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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTestProject {
    public static String HOME_PAGE = "http://demo.nopcommerce.com";
    public static String WISHLIST = "http://demo.nopcommerce.com/wishlist";
    public static String SHOPPING_CART = "http://demo.nopcommerce.com/cart";
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
        testOne();
        testTwo();
        
        // Se utiliza el mismo webDriver para casos 3 y 4 ya que se requiere la misma sesión.
        WebDriver driver = new ChromeDriver(); 
        testThree(driver);
        testFour(driver);
        
        //Otros tests
        } catch (Exception e) {
         e.printStackTrace();
        }
        
    }

    public static void testOne () throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to(HOME_PAGE);
        String pageTitle = driver.getTitle();
        
        try {
            Assert.assertEquals("nopCommerce demo store", pageTitle);
            System.out.println("El titulo es correcto: " + pageTitle);
        } catch (java.lang.AssertionError e) {
            System.out.println("El titulo es incorrecto: " + pageTitle);
            System.err.println("Caso TC1. Error ejecutando caso");
            System.err.println(e.getMessage());
        }
           
        //No es el más recomendable de utilizar para selenium, pero como solo se desea esperar
        //5 segundos, sin ninguna condición adicional, se utiliza para cumplir el caso.
        Thread.sleep(500);

        System.out.println("Caso TC1. Ejecutado correctamente");
        driver.close();
        driver.quit();

    }
      
    public static void testTwo () throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        String category = "Home";
        
        //Condición la cual espera a que el llamado document.readyState de JS retorne complete
        //Lo cual indica que el DOM de la página terminó de cargar.
        ExpectedCondition<Boolean> pageLoad = (WebDriver driver1) -> 
                ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete");
        
        try {
            
            Wait wait = new WebDriverWait(driver, 10);

            driver.navigate().to(HOME_PAGE);
            wait.until(pageLoad);

            //Se ingresa a las diferentes categorias esperando siempre a que la 
            //página termine de cargar antes de continuar con la siguiente
            for(int i = 0; i < CATEGORIES.length; i++) {
                category = CATEGORIES[i];
                driver.navigate().to(CATEGORIES_URLS[i]);
                wait.until(pageLoad);
            }
            
            category = "Clicking image";
            WebElement imageLink = driver.findElement(By.className("header-logo"));
            imageLink.click();
            
            System.out.println("Caso TC2. Ejecutado correctamente");

        } catch (Exception e) {
            System.err.println("Caso TC2. Error ejecutando caso");
            System.out.println("Error abriendo categoria: " + category);
            System.err.println(e.getMessage());
        }
         
        driver.close();
        driver.quit();
    }
    
    public static void testThree(WebDriver driver) throws InterruptedException {
        String textToFind = "The wishlist is empty!";
        String idToFind = "small-searchterms";
        String itemToSearch = "Fahrenheit 451";
        Wait wait = new WebDriverWait(driver, 10);
        try {
            driver.navigate().to(WISHLIST);
        
            //Busca que el texto esperado se encuentra en algún lugar de la página
            String bodyText = driver.findElement(By.tagName("body")).getText();
            Assert.assertTrue("Text not found!", bodyText.contains(textToFind));
            
            //Envia a la barra de busqueda el valor de búsqueda deseado
            WebElement searchBox = driver.findElement(By.id(idToFind));
            searchBox.sendKeys(itemToSearch);
            driver.findElement(By.cssSelector("input[type='submit']")).click();
            
            //Agregar el elemento encontrado a la busqueda al wishlist. Espera a que se muestre el mensaje de exito
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Add to wishlist']")));
            driver.findElement(By.cssSelector("input[value='Add to wishlist']")).click();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bar-notification"))));
            
            //Verifica que el mensaje de que no habian items en el wishlist ya no aparezca
            driver.navigate().to(WISHLIST);
            bodyText = driver.findElement(By.tagName("body")).getText();
            Assert.assertFalse("Text not found!", bodyText.contains(textToFind));
            
            System.out.println("Caso TC3. ejecutado correctamente");
        } catch (java.lang.AssertionError e) {
            System.err.println("Caso TC3. Error ejecutando caso");
            System.err.println(e.getMessage());
            //e.printStackTrace();
        }
    }
    
    public static void testFour(WebDriver driver) {
        String textToFind = "Your Shopping Cart is empty!";
        String itemToSearch = "Fahrenheit 451 by Ray Bradbury";
        Wait wait = new WebDriverWait(driver, 10);
        try {
            driver.navigate().to(SHOPPING_CART);
            String bodyText = driver.findElement(By.tagName("body")).getText();
            Assert.assertTrue("Text not found!", bodyText.contains(textToFind));
            
            //Ingresa al wishlist, selecciona el item a agregar al carrito y lo agrega.
            driver.navigate().to(WISHLIST);
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='addtocart']")));
            driver.findElement(By.cssSelector("input[name='addtocart']")).click();
            driver.findElement(By.cssSelector("input[name='addtocartbutton']")).click();
            
            //Verifica que se haya agregado al carrito, y le da click en continue shopping
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='continueshopping']")));
            bodyText = driver.findElement(By.tagName("body")).getText();
            Assert.assertTrue("Text not found!", bodyText.contains(itemToSearch));
            driver.findElement(By.cssSelector("input[name='continueshopping']")).click();
            
            System.out.println("Caso TC4. ejecutado correctamente");            
        } catch (java.lang.AssertionError e) {
            System.err.println("Caso TC4. Error ejecutando caso");
            System.err.println(e.getMessage());
            //e.printStackTrace();
        }
    }
    
}
