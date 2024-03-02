package firstcode;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utilities.DriverSetup;
import utilities.Excel;

public class miniproject {
	
	String parentwindowid;
	String final_cart_price;
	String first_product_amount;
	String second_product_amount;
	
	static String file = System.getProperty("user.dir") +"/src/test/resources/testdata/data.xlsx";
	
	
	public static WebDriver driver;
	
	public void setup() throws IOException {
		driver=DriverSetup.getWebDriver();
	}
	
	public void popup_page() throws IOException {
		  //driver.findElement(By.xpath("//span[@class='_30XB9F']")).click();
		// TODO Auto-generated method stub
		
		  //String flipkartpage =driver.getTitle();
		  if(driver.getTitle().contains("Online Shopping Site"))
		  {
			  System.out.println("Home page loaded succesfull");
			  Excel.setCellData(file,"Page_Validation", 1, 1, "Pass");
			  Excel.fillGreenColor(file, "Page_Validation", 1, 1);
		  }
		  else
		  {
			  System.out.println("Home page not loaded");
			  Excel.setCellData(file,"Page_Validation", 1, 1, "fail");
			  Excel.fillRedColor(file, "Page_Validation", 1, 1);
		  }
		
	}
	
	public void sendtexttosearch() throws IOException {
		String Input = Excel.getCellData(file,"Sheet1",1,0);
		driver.findElement(By.cssSelector("input[placeholder='Search for Products, Brands and More']")).sendKeys(Input);
		// TODO Auto-generated method stub
		
	}
	
	public void searchbutton() throws InterruptedException {
		 //driver.findElement(By.xpath("//button[@title='Search for Products, Brands and More']//*[name()='svg']")).click();
		 Thread.sleep(10000);
		 driver.findElement(By.xpath("//button[@class='_2iLD__']")).click();
		// TODO Auto-generated method stub
		
	}
	
	public void firstproductwindow() {
		driver.findElement(By.xpath("(//a[@class='s1Q9rs'])[1]")).click();
		// TODO Auto-generated method stub
		
	}
	
	public void towindowid() {
		    Set<String> windowid = driver.getWindowHandles();
	       List<String>windowList =new ArrayList<String>(windowid);
	        parentwindowid = windowList.get(0);
	        String childwindowid = windowList.get(1);
	        driver.switchTo().window(childwindowid);
	          
		// TODO Auto-generated method stub
		
	}
	
	public void firstproductprice() throws InterruptedException {
		Thread.sleep(10000);
		 WebElement priceofproduct1=driver.findElement(By.xpath("//div[@class='_30jeq3 _16Jk6d']"));
		  	 first_product_amount=priceofproduct1.getText();
			  System.out.println("First product price"+first_product_amount);
		// TODO Auto-generated method stub
		
	}
	
	public void firstaddcart() throws InterruptedException {
		  //Thread.sleep(5000);
		    
		      
		      WebElement cart1= driver.findElement(By.xpath("//button[normalize-space()='Add to cart']"));
		      JavascriptExecutor js=(JavascriptExecutor)driver;
			  js.executeScript("arguments[0].scrollIntoView();",cart1);
			  cart1.click();
			  
		   Thread.sleep(5000);
		  //close first product window
		  driver.close();
		// TODO Auto-generated method stub
		
	}
	
	public void switchwindow() throws InterruptedException {
		driver.switchTo().window(parentwindowid);
		  Thread.sleep(3000); 
		  //second product click
		  driver.findElement(By.xpath("(//a[@class='s1Q9rs'])[2]")).click();

		// TODO Auto-generated method stub
		
	}
	
	public void printwindowtitle() {
		 Set<String> printhandles= driver.getWindowHandles();
		 for(String handle:printhandles)
		 {
			 driver.switchTo().window(handle);
			 String title=driver.getTitle();
			 //System.out.println("Title:"+title);
			 //if statement
		 if(title.equals("Title:Home Appliences- Buy Products Online at Best Price in India - All Categories | Flipkart.com)"))
		 {
			 driver.switchTo().window(handle);
		 }
		 }
		// TODO Auto-generated method stub
		
	}
	
	public void secondproductprice() {
		 WebElement priceofproduct2=driver.findElement(By.cssSelector("._30jeq3._16Jk6d"));
	 	   second_product_amount=priceofproduct2.getText();
		  System.out.println("second product price"+second_product_amount);
		// TODO Auto-generated method stub
		
	}
	
	public void secondaddcart() throws InterruptedException {
		 WebElement carts= driver.findElement(By.xpath("//button[normalize-space()='Add to cart']"));
		 JavascriptExecutor js=(JavascriptExecutor)driver;
		  js.executeScript("arguments[0].scrollIntoView();",carts);
		  carts.click();
		  Thread.sleep(3000);		// TODO Auto-generated method stub
		
	}
	
	public void printfinalprice() throws InterruptedException {
		 WebElement finalcart=driver.findElement(By.xpath("(//div[@class='z4Ha90'])[1]"));
		   final_cart_price=finalcart.getText();
		  System.out.println("final cart price "+final_cart_price);
		  Thread.sleep(5000);
		// TODO Auto-generated method stub
		
	}
	
	public void finalvalidation() throws IOException {
		//removing ₹ and , symbols for first product amount
		  String val1=first_product_amount.replace( "₹","");
		  val1=val1.replace("," ,"");
		  
		  
		//removing ₹ and , symbols for second product amount
		  String val2=second_product_amount.replace("," ,"");
		  val2=val2.replace("₹" ,"");
		  
		  
		//removing ₹ and , symbols for final cart price
		  String val3=final_cart_price.replace(",", "");
		  val3=val3.replace("₹" ,"");
		  
		  
		//converting string to int ,for adding
		  int product1=Integer.parseInt(val1);
		  int product2=Integer.parseInt(val2);
		  //int finalprice=product1+product2;
		  int finalprice=Integer.parseInt(val3);
		  
		  if(finalprice == product1+product2)
		  {
			 System.out.println("amount validated");
			 Excel.setCellData(file,"Amount_Validation", 1, 1, "Pass");
			  Excel.fillGreenColor(file, "Amount_Validation", 1, 1);
		  }
		  else 
		  {
			  System.out.println("amount not validated");
			  Excel.setCellData(file,"Amount_Validation", 1, 1, "fail");
			  Excel.fillRedColor(file, "Amount_Validation", 1, 1);
			  
		  }
		  //driver.quit();
		 
		// TODO Auto-generated method stub
		
	}

    public void closebroswer() {
    	driver.quit();
    }


	public static void main(String[] args) throws InterruptedException, IOException {
		
		miniproject fp = new miniproject();
		
		fp.setup();
		
		fp.popup_page();
		
		fp.sendtexttosearch();
		fp.searchbutton();
		fp.firstproductwindow();
		fp.towindowid();
		fp.firstproductprice();
		
		fp.firstaddcart();
		
		fp.switchwindow();
		fp.printwindowtitle();
		fp.secondproductprice();
		fp.secondaddcart();
		fp.printfinalprice();
		fp.finalvalidation();
		fp.closebroswer();
	
	}
}
	


	