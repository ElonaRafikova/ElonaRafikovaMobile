package enums;


public enum Browsers {
    CHROME("Chrome"),
    SAFARI("Safari");

    private String text;

    Browsers(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
