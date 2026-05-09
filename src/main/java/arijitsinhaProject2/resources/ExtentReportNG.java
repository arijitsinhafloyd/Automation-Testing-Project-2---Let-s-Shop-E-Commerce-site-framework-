package arijitsinhaProject2.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	public static ExtentReports getReportObject() {
		ExtentSparkReporter reporter= new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\index.html");
		reporter.config().setReportName("Let's Shop Automation Report");
		reporter.config().setDocumentTitle("Test Execution Report");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Arijit Sinha");
		return extent;
	}
}
