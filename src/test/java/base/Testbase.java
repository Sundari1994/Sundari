package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import commonutils.Testutil;

public class Testbase {
public static Properties prop;
public static WebDriver driver;
public void TestBase() {
	prop = new Properties();
	String path = System.getProperty("user.dir")+"//src//test//resources//config//config.property";
	try {
		FileInputStream fin = new FileInputStream(path);
		prop.load(fin);
	}
	catch(FileNotFoundException e)
	{
		e.printStackTrace();
	}catch (IOException e)
	{
		e.printStackTrace();
	}
}
public static void initialize()
{
	String browser = prop.getProperty("browser");
	if(browser.equalsIgnoreCase("Chrome"))
	{WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	}
	else if(browser.equalsIgnoreCase("edge"))
	{
		WebDriverManager.edgedriver().setup();
	driver = new EdgeDriver();
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Testutil.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(Testutil.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
	driver.get(prop.getProperty("url"));
}

}

