package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/**
	 * PLEASE DO NOT DELETE THIS method. Helper method for Udacity-supplied sanity
	 * checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password) {
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-msg")));
		/*
		 * Check that the sign up was successful. // You may have to modify the element
		 * "success-msg" and the sign-up // success message below depening on the rest
		 * of your code.
		 */
		Assertions
				.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}

	/**
	 * PLEASE DO NOT DELETE THIS method. Helper method for Udacity-supplied sanity
	 * checks.
	 **/
	private void doLogIn(String userName, String password) {
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	private void createNote(String title, String description) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/home");
		WebElement notesNavTab = driver.findElement(By.id("nav-notes-tab"));
		notesNavTab.click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes")));
		WebElement addNewNoteBtn = driver.findElement(By.id("addNewNoteBtn"));
		addNewNoteBtn.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("noteModal")));

		WebElement noteTitle = driver.findElement(By.id("note-title"));
		noteTitle.click();
		noteTitle.sendKeys(title);

		WebElement noteDescription = driver.findElement(By.id("note-description"));
		noteDescription.click();
		noteDescription.sendKeys(description);

		WebElement noteSubmit = driver.findElement(By.id("noteSaveChangesBtn"));
		noteSubmit.click();
	}

	private void createCredential(String url, String userName, String password) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/home");
		WebElement credentialsNavTab; 
		
		credentialsNavTab = driver.findElement(By.id("nav-credentials-tab"));
		credentialsNavTab.click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials")));
		WebElement addNewCredentialBtn = driver.findElement(By.id("addNewCredentialBtn"));
		addNewCredentialBtn.click();
		 
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialModal")));
		
		WebElement urlTextInput = driver.findElement(By.id("credential-url"));		
		WebElement userNameTextInput = driver.findElement(By.id("credential-username"));		
		WebElement passwordTextInput = driver.findElement(By.id("credential-password"));		
		WebElement credentialSubmitBtn = driver.findElement(By.id("credentialSaveBtn"));
		
		urlTextInput.sendKeys(url);
		userNameTextInput.sendKeys(userName);
		passwordTextInput.sendKeys(password);
		
		credentialSubmitBtn.click();
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
		
		credentialsNavTab = driver.findElement(By.id("nav-credentials-tab"));
		credentialsNavTab.click();
	}
	
	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code. This test is provided by Udacity to perform some basic
	 * sanity testing of your code to ensure that it meets certain rubric criteria.
	 * 
	 * If this test is failing, please ensure that you are handling redirecting
	 * users back to the login page after a succesful sign up. Read more about the
	 * requirement in the rubric: https://review.udacity.com/#!/rubrics/2724/view
	 */
	@Test
	public void testRedirection() {
		// Create a test account
		doMockSignUp("Redirection", "Test", "RT", "123");

		// Check if we have been redirected to the log in page.
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code. This test is provided by Udacity to perform some basic
	 * sanity testing of your code to ensure that it meets certain rubric criteria.
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at:
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {
		// Create a test account
		doMockSignUp("URL", "Test", "UT", "123");
		doLogIn("UT", "123");

		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code. This test is provided by Udacity to perform some basic
	 * sanity testing of your code to ensure that it meets certain rubric criteria.
	 * 
	 * If this test is failing, please ensure that you are handling uploading large
	 * files (>1MB), gracefully in your code.
	 * 
	 * Read more about file size limits here:
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload
	 * Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File", "Test", "LFT", "123");
		doLogIn("LFT", "123");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
	}

	@Test
	public void testUnauthorizedUserCanOnlyAccessLoginAndSignup() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertThrows(NoSuchElementException.class, () -> driver.findElement(By.id("nav-tabContent")));

		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertTrue(driver.getTitle().contains("Sign Up"));

		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertTrue(driver.getTitle().contains("Login"));
	}

	@Test
	public void testUserCannotAccessHomeAfterLogout() {
		doMockSignUp("John", "Doe", "john1", "doe1");
		doLogIn("john1", "doe1");

		// verify that homepage is accessible
		Assertions.assertTrue(driver.getTitle().contains("Home"));

		WebElement logoutButton = driver.findElement(By.id("logoutBtn"));
		logoutButton.click();

		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		webDriverWait.until(ExpectedConditions.titleContains("Login"));

		driver.get("http://localhost:" + this.port + "/home");

		// driver.findElement(By.id(""))
		Assertions.assertFalse(driver.getTitle().contains("Home"));
		Assertions.assertTrue(driver.getTitle().contains("Login"));
	}

	@Test
	public void testUserCanCreateAndViewNote() {
		doMockSignUp("John", "Doe", "john", "doe");
		doLogIn("john", "doe");
		createNote("todo", "buy oranges.");

		// wait for page to load

		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/home#nav-notes");
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		WebElement notesNavTabRef = driver.findElement(By.id("nav-notes-tab"));

		notesNavTabRef.click();
		WebElement notesTable = driver.findElement(By.id("notesTable"));
		List<WebElement> notesTableRows = notesTable.findElements(By.tagName("tr"));
		
		// Verify that note has been added to the list
		Assertions.assertEquals(notesTableRows.size(), 2);
	}
	
	@Test
	public void testUserCanEditNote() {
		WebElement notesTable;
		List<WebElement> notesTableRows;
		WebElement lastRow;		
		
		doMockSignUp("John", "Doe", "john2", "doe2");
		doLogIn("john2", "doe2");
		createNote("shopping list", "buy beans");
		
		driver.get("http://localhost:" + this.port + "/home#nav-notes");
		
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		
		WebElement notesNavTabRef = driver.findElement(By.id("nav-notes-tab"));
		notesNavTabRef.click();
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("notesTable")));
		notesTable = driver.findElement(By.id("notesTable"));
		notesTableRows = notesTable.findElements(By.tagName("tr"));
		
		lastRow = notesTableRows.get(notesTableRows.size() -1 );
		
		WebElement editButton = lastRow.findElement(By.tagName("button"));
		editButton.click();
		
		// edit note
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("noteModal")));
		
		WebElement noteTitle = driver.findElement(By.id("note-title"));
		noteTitle.click();
		noteTitle.clear();
		noteTitle.sendKeys("grocery list");

		WebElement noteDescription = driver.findElement(By.id("note-description"));
		noteDescription.click();
		noteDescription.clear();
		noteDescription.sendKeys("buy carrots");

		WebElement noteSubmit = driver.findElement(By.id("noteSaveChangesBtn"));
		noteSubmit.click();		
		
		driver.get("http://localhost:" + this.port + "/home#nav-notes");
		
		WebElement notesNavTab = driver.findElement(By.id("nav-notes-tab"));
		notesNavTab.click();
		
		// verify changes
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("notesTable")));
		
		notesTable = driver.findElement(By.id("notesTable"));
		notesTableRows = notesTable.findElements(By.tagName("tr"));
		
		lastRow = notesTableRows.get(notesTableRows.size() -1 );
		
		Assertions.assertEquals(lastRow.findElement(By.tagName("th")).getText(), "grocery list");
		Assertions.assertEquals(lastRow.findElements(By.tagName("td")).get(1).getText(), "buy carrots");		
	}
	
	@Test
	public void userCanDeleteNote() {
		WebElement notesTable;
		List<WebElement> notesTableRows;
		WebElement lastRow;		
		
		doMockSignUp("John", "Doe", "john3", "doe3");
		doLogIn("john3", "doe3");
		createNote("shopping list", "buy beans");
		
		driver.get("http://localhost:" + this.port + "/home#nav-notes");
		
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		
		WebElement notesNavTabRef = driver.findElement(By.id("nav-notes-tab"));
		notesNavTabRef.click();
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("notesTable")));
		notesTable = driver.findElement(By.id("notesTable"));
		notesTableRows = notesTable.findElements(By.tagName("tr"));
		
		lastRow = notesTableRows.get(notesTableRows.size() -1 );
		
		WebElement deleteLink = lastRow.findElement(By.tagName("a"));
		deleteLink.click();
		
		driver.get("http://localhost:" + this.port + "/home#nav-notes");
		
		WebElement notesNavTab = driver.findElement(By.id("nav-notes-tab"));
		notesNavTab.click();
		
		// verify changes
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("notesTable")));
		
		notesTable = driver.findElement(By.id("notesTable"));
		notesTableRows = notesTable.findElements(By.tagName("tr"));
		
		Assertions.assertEquals(notesTableRows.size(), 1);		
	} 
	
	@Test
	public void testUserCanCreateCredential() {
		WebElement credentialsTable;
		List<WebElement> credentialsTableRows;
		WebElement lastRow;		
		
		doMockSignUp("John", "Doe", "john4", "doe");
		doLogIn("john4", "doe");
		createCredential("panic.com", "panicuser", "panicpass");
		
		credentialsTable = driver.findElement(By.id("credentialsTable"));
		credentialsTableRows = credentialsTable.findElements(By.tagName("tr"));
		
		Assertions.assertEquals(credentialsTableRows.size(), 2);		
	}
	
	@Test
	public void testUserCanEditCredential() {
		WebElement credentialsTable;
		List<WebElement> credentialsTableRows;
		WebElement lastRow;		
		
		doMockSignUp("John", "Doe", "john5", "doe");
		doLogIn("john5", "doe");
		createCredential("panic.com", "panicuser", "panicpass");
		
		// navigate to edit credential button
		
		driver.get("http://localhost:" + this.port + "/home#nav-credentials");
		
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		
		WebElement credentialsNavTabLink = driver.findElement(By.id("nav-credentials-tab"));
		credentialsNavTabLink.click();
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialsTable")));
		credentialsTable = driver.findElement(By.id("credentialsTable"));
		credentialsTableRows = credentialsTable.findElements(By.tagName("tr"));
		
		lastRow = credentialsTableRows.get(credentialsTableRows.size() -1 );
		
		WebElement editButton = lastRow.findElement(By.tagName("button"));
		editButton.click();
		
		// edit credential
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialModal")));
		
		WebElement credentialUrl = driver.findElement(By.id("credential-url"));
		credentialUrl.click();
		credentialUrl.clear();
		credentialUrl.sendKeys("stonewall.com");

		WebElement credentialUsername = driver.findElement(By.id("credential-username"));
		credentialUsername.click();
		credentialUsername.clear();
		credentialUsername.sendKeys("ironman");
		
		WebElement credentialPassword = driver.findElement(By.id("credential-password"));
		credentialPassword.click();
		credentialPassword.clear();
		credentialPassword.sendKeys("superpass");

		WebElement credentialSubmit = driver.findElement(By.id("credentialSaveBtn"));
		credentialSubmit.click();		
		
		driver.get("http://localhost:" + this.port + "/home#nav-credential");
		
		WebElement notesNavTab = driver.findElement(By.id("nav-credentials-tab"));
		notesNavTab.click();
		
		// verify credential update
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialsTable")));
		
		credentialsTable = driver.findElement(By.id("credentialsTable"));
		credentialsTableRows = credentialsTable.findElements(By.tagName("tr"));
		
		lastRow = credentialsTableRows.get(credentialsTableRows.size() -1 );
		
		Assertions.assertEquals(lastRow.findElement(By.tagName("th")).getText(), "stonewall.com");
		Assertions.assertEquals(lastRow.findElements(By.tagName("td")).get(1).getText(), "ironman");
		
	}
	
	@Test
	public void testUserCanDeleteCredential() {
		WebElement credentialsTable;
		List<WebElement> credentialsTableRows;
		WebElement lastRow;
		
		doMockSignUp("John", "Doe", "john6", "doe");
		doLogIn("john6", "doe");
		createCredential("panic.com", "ironman", "ironpass");
		
		driver.get("http://localhost:" + this.port + "/home#nav-credentials");
		
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		
		WebElement credentialsNavTabRef = driver.findElement(By.id("nav-credentials-tab"));
		credentialsNavTabRef.click();
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialsTable")));
		credentialsTable = driver.findElement(By.id("credentialsTable"));
		credentialsTableRows = credentialsTable.findElements(By.tagName("tr"));
		
		lastRow = credentialsTableRows.get(credentialsTableRows.size() -1 );
		
		WebElement deleteLink = lastRow.findElement(By.tagName("a"));
		deleteLink.click();
		
		driver.get("http://localhost:" + this.port + "/home#nav-credentials");
		
		WebElement credentialsNavTab = driver.findElement(By.id("nav-credentials-tab"));
		credentialsNavTab.click();
		
		// verify credential has been deleted
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialsTable")));
		
		credentialsTable = driver.findElement(By.id("credentialsTable"));
		credentialsTableRows = credentialsTable.findElements(By.tagName("tr"));
		
		Assertions.assertEquals(credentialsTableRows.size(), 1);		
	}
	

}
