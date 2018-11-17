package setup;

import enums.PropertiesFile;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class Driver {

    private static AppiumDriver driverSingle = null;
    private static WebDriverWait waitSingle = null;
    private static DesiredCapabilities capabilities;
    private static TestProperties testProperties = new TestProperties();


    protected void setPropertiesFile(PropertiesFile f) {
        testProperties.setPropertiesFile(f);
    }

    protected String getSUT() {
        return testProperties.getSUT();
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
        capabilities = testProperties.setCapabilities();
        // Init driver for local Appium server with capabilities have been set
        if (driverSingle == null) driverSingle = new AppiumDriver(new URL(testProperties.getDRIVER()), capabilities);
    }

}


