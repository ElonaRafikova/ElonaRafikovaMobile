package setup;

import enums.Capabilities;
import enums.PropertiesFile;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static enums.Browsers.CHROME;
import static enums.Browsers.SAFARI;
import static enums.Capabilities.*;
import static enums.Exceptions.UNCLEAR_TYPE;
import static enums.Exceptions.UNKNOWN_PLATFORM;
import static enums.Paths.*;


class TestProperties {

    private Properties currentProps = new Properties();
    private String propertiesFile;
    private String AUT; // (mobile) app under testing
    private String SUT; // site under testing
    private String TEST_PLATFORM;
    private String DRIVER;
    private String DEVICE_NAME;
    private String APP_PACKAGE;
    private String APP_ACTIVITY;
    private String UDID;
    private static final String ANDRIOD = "Android";
    private static final String IOS = "iOS";
    private static final String http = "https://";

    //set needed type of properties for web or native
    protected void setPropertiesFile(PropertiesFile file) {
        propertiesFile = file.getType();
    }

    protected String getSUT() {
        return SUT;
    }

    protected String getDRIVER() {
        return DRIVER;
    }

    private Properties getCurrentProps() throws IOException {

        File properties = new File(propertiesFile);
        FileInputStream in = new FileInputStream(properties.getAbsolutePath());
        currentProps.load(in);
        in.close();
        return currentProps;
    }

    private String getProp(Capabilities propKey) throws IOException {
        if (!currentProps.containsKey(propKey.getText()))
            currentProps = getCurrentProps();
        // "default" form used to handle the absence of parameter
        return currentProps.getProperty(propKey.getText(), null);
    }

    protected DesiredCapabilities setCapabilities() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        String resourcePath = RESOURCES.getText();
        String appName = getProp(Capabilities.AUT);
        AUT = appName == null ? null : resourcePath + appName;

        String t_sut = getProp(Capabilities.SUT);
        SUT = t_sut == null ? null : http + t_sut;

        TEST_PLATFORM = getProp(PLATFORM);
        DRIVER = getProp(Capabilities.DRIVER);
        DEVICE_NAME = getProp(Capabilities.DEVICE_NAME);

        APP_PACKAGE = getProp(Capabilities.APP_PACKAGE);
        APP_ACTIVITY = getProp(Capabilities.APP_ACTIVITY);
        UDID = getProp(Capabilities.UDID);

        String browserName;
        // Setup test platform: Android or iOS. Browser also depends on a platform.
        switch (TEST_PLATFORM) {
            case ANDRIOD:
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
                // default Android emulator
                browserName = CHROME.getText();
                break;
            case IOS:
                browserName = SAFARI.getText();
                capabilities.setCapability(MobileCapabilityType.UDID, UDID);
                break;
            default:
                throw new Exception(UNKNOWN_PLATFORM.getText());
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);

        // Setup type of application: mobile, webTests (or hybrid)
        if (AUT != null && SUT == null) {
            // Native

            //remote
            if (APP_ACTIVITY != null && APP_PACKAGE != null) {
                capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
                capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
            } else {
                File app = new File(AUT);
                capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            }

        } else if (SUT != null && AUT == null) {
            // Web
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
            File drivers = new File(DRIVERS.getText());
            capabilities.setCapability(CHROME_DIR.getText(), drivers.getAbsolutePath());
            File mapping = new File(MAPPING.getText());
            capabilities.setCapability(CHROME_MAPPING.getText(), mapping.getAbsolutePath());
        } else {
            throw new Exception(UNCLEAR_TYPE.getText());
        }
        return capabilities;
    }
}
