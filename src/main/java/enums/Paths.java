package enums;

public enum Paths {
    RESOURCES("src\\main\\resources\\"),
    DRIVERS(RESOURCES.getText() + "drivers"),
    MAPPING(RESOURCES.getText() + "mapping.json");


    private String text;

    Paths(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
