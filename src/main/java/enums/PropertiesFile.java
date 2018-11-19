package enums;

public enum PropertiesFile {
    WEB("webtests.properties"),
    NATIVE("nativetests.properties"),
    WEB_IPHONE5S("IPhone5S_webtests.properties"),
    NATIVE_NEXUS5("Nexus5_nativetests.properties");

    private String type;

    PropertiesFile(String type) {
        this.type = type;
    }

    public static PropertiesFile find(String type) throws Exception {
        PropertiesFile[] values = PropertiesFile.values();
        for (PropertiesFile value : values) {
            if (type.equals(value.getType())) {
                return value;
            }
        }
        throw new Exception();
    }

    public String getType() {
        return type;
    }

}
