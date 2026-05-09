package arijitsinhaProject2.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import arijitsinhaProject2.pageObjects.LandingPage;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class BaseTest {

	public WebDriver driver;
	public LandingPage lp;
	public WebDriver initializeDriver() throws IOException {
		
		//Properties class in Java is used to read key-value pairs from a .properties file, 
		//usually for configuration data like URLs, usernames, passwords, browser name, etc.
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\arijitsinhaProject2\\resources\\GlobalData.properties");
		prop.load(fis);
		String browser=System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		
		if(browser.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
			if(browser.contains("headless")) {
			options.addArguments("--headless=new");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--disable-gpu");
			}
			System.out.println(browser);
		   driver=new ChromeDriver(options);
		}
		
		else if(browser.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchingApplication() throws IOException {
		driver=initializeDriver();
		lp=new LandingPage(driver);
		lp.goTo();
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	
	public List<HashMap<String,String>> jsonData() throws IOException {
		String jSonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\arijitsinhaProject2\\dataDriven\\DataForTest.json"),
				StandardCharsets.UTF_8);
		ObjectMapper map=new ObjectMapper();
		List<HashMap<String,String>> data=map.readValue(jSonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String getScreenshots(String testCaseName, WebDriver driver) throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		FileUtils.copyFile(src, new File(path));
		return path;
	}
	 
}
