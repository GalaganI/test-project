package utils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

 
 
public class Wait {
 private static Long implicitWait = (long) 20;
 
 public static Long getImplicitWait() {
	 return implicitWait;
 }
 public static void untilJqueryIsDone(WebDriver driver){
 untilJqueryIsDone(driver,getImplicitWait() );
 }
 public static WebElement waitUntilElementPresent(WebDriver driver , By locator) {
	return (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(locator));
 }
 public static WebElement waitUntilElementIsVisible(WebDriver driver ,By locator) {
	 return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
 }
 public static List<WebElement> waitUntilAllElementsAreVisible(WebDriver driver ,By locator) {
	 return  (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
 }
 public static void untilJqueryIsDone(WebDriver driver, Long timeoutInSeconds){
 until(driver, (d) ->
 {
 Boolean isJqueryCallDone = (Boolean)((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
 if (!isJqueryCallDone) System.out.println("JQuery call is in Progress");
 return isJqueryCallDone;
 }, timeoutInSeconds);
 }
 
 public static void untilPageLoadComplete(WebDriver driver) {
 untilPageLoadComplete(driver, getImplicitWait());
 }
 
 public static void untilPageLoadComplete(WebDriver driver, Long timeoutInSeconds){
 until(driver, (d) ->
 {
 Boolean isPageLoaded = (Boolean)((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
 if (!isPageLoaded) System.out.println("Document is loading");
 return isPageLoaded;
 }, timeoutInSeconds);
 }
 
 public static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition){
 until(driver, waitCondition, getImplicitWait());
 }
 
 
 private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds){
 WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
 webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
 try{
 webDriverWait.until(waitCondition);
 }catch (Exception e){
 System.out.println(e.getMessage());
 }          
 }
 
 
}