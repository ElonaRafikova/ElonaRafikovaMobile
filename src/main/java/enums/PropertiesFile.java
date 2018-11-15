package enums;

public enum PropertiesFile {
    WEB("webtests.properties"),
    NATIVE("nativetests.properties");

    private String type;

    PropertiesFile(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
