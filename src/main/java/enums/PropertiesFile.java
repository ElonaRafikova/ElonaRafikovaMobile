package enums;

public enum PropertiesFile {
    WEB("webtests.properties"),
    NATIVE("nativetests.properties"),
    WEB_IPHONE(""),
    NATIVE_ANDROID("");

    private String type;

    PropertiesFile(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
