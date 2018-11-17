package enums;

public enum Exceptions {
    UNKNOWN_PLATFORM("Unknown mobile platform"),
    UNCLEAR_TYPE("Unclear type of mobile app");


    private String text;

    Exceptions(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
