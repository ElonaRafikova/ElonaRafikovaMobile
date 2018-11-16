package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Driver extends TestProperties {

    private static AppiumDriver driverSingle = null;
    private static WebDriverWait waitSingle = null;
    private static DesiredCapabilities capabilities;

    // Properties to be read
    private String AUT; // (mobile) app under testing
    private String SUT; // site under testing
    private String TEST_PLATFORM;
    private String DRIVER;
    private String DEVICE_NAME;

    protected String getSUT() {
        return SUT;
    }

    protected AppiumDriver driver() throws Exception {
        if (driverSingle == null) prepareDriver();
        return driverSingle;
    }

    protected WebDriverWait driverWait() throws Exception {
        if (waitSingle == null) prepareWait();
        return waitSingle;
    }

    private void prepareWait() throws Exception {
        if (waitSingle == null) waitSingle = new WebDriverWait(driver(), 10);
    }

    protected void prepareDriver() throws Exception {

        String resourcePath = "./src/main/resources/";
        String appName = getProp("aut");
        AUT = appName == null ? null : resourcePath + appName;

        String t_sut = getProp("sut");
        SUT = t_sut == null ? null : "http://" + t_sut;

        TEST_PLATFORM = getProp("platform");
        DRIVER = getProp("driver");
        DEVICE_NAME = getProp("deviceName");

        capabilities = new DesiredCapabilities();
        String browserName;
        // Setup test platform: Android or iOS. Browser also depends on a platform.
        switch (TEST_PLATFORM) {
            case "Android":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
                // default Android emulator
                browserName = "Chrome";
                break;
            case "iOS":
                browserName = "Safari";
                break;
            default:
                throw new Exception("Unknown mobile platform");
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);

        // Setup type of application: mobile, webTests (or hybrid)
        if (AUT != null && SUT == null) {
            // Native
            File app = new File(AUT);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        } else if (SUT != null && AUT == null) {
            // Web
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
            File drivers = new File("src\\main\\resources\\drivers");
            capabilities.setCapability("chromedriverExecutableDir", drivers.getAbsolutePath());
            File mapping = new File("src\\main\\resources\\mapping.json");
            capabilities.setCapability("chromedriverChromeMappingFile", mapping.getAbsolutePath());
        } else {
            throw new Exception("Unclear type of mobile app");
        }

        // Init driver for local Appium server with capabilities have been set
        if (driverSingle == null) driverSingle = new AppiumDriver(new URL(DRIVER), capabilities);
    }

}


