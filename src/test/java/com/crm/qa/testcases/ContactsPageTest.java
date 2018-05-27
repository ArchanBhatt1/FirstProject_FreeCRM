package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	HomePageTest homePageTest;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	public ContactsPageTest(){
		super();
	}
	
	
	@BeforeMethod
	public void setUp() throws InterruptedException{
		initialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink(); 
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabel(){
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "contact label is missing");
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest(){
		contactsPage.selectContactsByName("aaa aaa");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest(){
		contactsPage.selectContactsByName("AABC xyz");
		contactsPage.selectContactsByName("A AB");
		contactsPage.selectContactsByName("A333 A444");
	}
	
	@DataProvider
	public Object[][] getCRMTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	//we need to pass the dataprovider here so that it is called inside test case. Dataprovider is fetching data from excel sheet which has 4 columns
	// so you need to pass those 4 columns here in this method
	
	@Test(priority=4,dataProvider="getCRMTestData")  
	public void validateCreateNewContact(String title,String firstName, String lastName, String company){ 
		                                                                                          
		homePage.clickOnNewContact();
		contactsPage.createNewContact(title, firstName, lastName, company);
	}
	
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
