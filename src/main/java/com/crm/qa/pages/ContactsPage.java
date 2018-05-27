package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{
	
	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	//@FindBy(xpath="//a[text()='aaa aaa']//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")
	//WebElement checkBox;
	
	@FindBy(name="title")
	WebElement contactTitle;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="surname")
	WebElement surname;
	
	@FindBy(name="client_lookup")
	WebElement company;
	
	@FindBy(xpath="//input[@type='submit' and @value='Save']")
	WebElement saveButton;
	
	
	public ContactsPage(){			
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactsLabel(){
		return contactsLabel.isDisplayed();
	}
	
	public void selectContactsByName(String name){ //we will not use page factory for this as tomorrow, if we want to select some different contact then we have to right
		                                         //another page factory for that. SO simply we will pass string in this method and change it with whatever name we want.
		driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
				+ "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
	}
	
	public void createNewContact(String title, String ftname, String ltname, String comp){
		Select select = new Select(contactTitle);
		select.selectByVisibleText(title);
		
		firstName.sendKeys(ftname);
		surname.sendKeys(ltname);
		company.sendKeys(comp);
		saveButton.click();
	}
	
	
	
	

}
