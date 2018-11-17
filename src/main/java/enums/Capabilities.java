package enums;

public enum Capabilities {
    AUT("aut"),
    SUT("sut"),
    PLATFORM("platform"),
    DRIVER("driver"),
    DEVICE_NAME("deviceName"),
    APP_PACKAGE("appPackage"),
    APP_ACTIVITY("appActivity"),
    UDID("udid"),
    CHROME_DIR("chromedriverExecutableDir"),
    CHROME_MAPPING("chromedriverChromeMappingFile");

    private String text;

    Capabilities(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
