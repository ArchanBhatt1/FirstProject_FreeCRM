package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{
	
	//@Cachelookup: (Advantage): It will create a small memory /cache and store userNameLabel in cache, so every time whenever you are interacting with this element instead of browser 
	//page it will get the element from the cache. So speed of your framework will improve. Performance of script will be improved.
	//It should be used only when you know that, that particular element will not get changed.
	//so if we have huge number of elements like this, which are being called like 100 times and every time it goes to browser to check whether that element is	
	//available or not. So by declaring @cachelookup we can store that element in cache memory and it will be picked from there only whenever needed.
	//(Disadvantage): But problem is: somehow that element property or page got refreshed. Then that cache memory will be corrupted. The Dom property might  	
	//get changed then cache memory of older version will get disturbed. And it will give you staleElementException.
	
	@FindBy(xpath="//font[contains(text(),'User: Naveen K')]")
	//@CacheLookup  //(but we will not be using it here this time)
	WebElement userNameLabel;  
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]//parent::li/descendant::a[text()='New Contact']")  //or //a[contains(text(),'New Contact')]
	WebElement newContactsLink;	
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	
	//Initializing the Page Objects
		public HomePage(){			
			PageFactory.initElements(driver, this);
		}
		
		//Actions
		
		public String verifyHomePageTitle(){
			return driver.getTitle();
		}
		
		public boolean verifyCorrectUserName(){
			return userNameLabel.isDisplayed();
		}
		
		public ContactsPage clickOnContactsLink(){
			contactsLink.click();			
			return new ContactsPage();
		}
		
		public DealsPage clickOnDealsLink(){
			dealsLink.click();			
			return new DealsPage();
		}
		
		public TasksPage clickOnTasksLink(){
			tasksLink.click();			
			return new TasksPage();
		}
		
		public void clickOnNewContact(){  //from home page only we will mouse hover contacts and then click on new contact so writing this method in home page
			Actions action = new Actions(driver);
			action.moveToElement(contactsLink).build().perform();
			newContactsLink.click();
		}
		
		
		
		

}
