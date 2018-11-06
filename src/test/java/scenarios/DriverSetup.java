package scenarios;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.System.setProperty;

public class DriverSetup {
    protected AndroidDriver driver;

    public void prepareAndroidNative() throws MalformedURLException { // exception required by java.net.URL

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory capabilities
        //capabilities.setCapability("device","Android"); // mandatory???????

        //capabilities.setCapability("deviceName","emulator-5554");
        capabilities.setCapability("deviceName", "ce091719d37b173c03");
        capabilities.setCapability("platformName", "Android");

        // path to app
        // Copy the application (.apk), which will become AUT, to the specified location,
        // e.g. "resources" folder of the project
        File appDir = new File("C:\\Users\\Elona\\IdeaProjects\\ElonaRafikovaMobile\\src\\main\\resources");
        File app = new File(appDir, "ContactManager.apk");

        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());

        // Init driver for local Appium server with capabilities have been set
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    protected void prepareAndroidWeb() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        //capabilities.setCapability("deviceName","emulator-5554");
        capabilities.setCapability("deviceName", "ce091719d37b173c03");
        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("chromedriverExecutableDir",
                "C:\\Users\\Elona\\IdeaProjects\\ElonaRafikovaMobile\\src\\main\\resources\\drivers");
        capabilities.setCapability("chromedriverChromeMappingFile",
                "C:\\Users\\Elona\\IdeaProjects\\ElonaRafikovaMobile\\src\\main\\resources\\mapping.json");

        // specific web capabilities
        capabilities.setCapability("browserName", "Chrome");
        //capabilities.setCapability("browserName", "Browser");

        // Init driver for local Appium server with capabilities have been set
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
}