package enums;

public enum BrowserSize {
    STANDARD("1366x768"),
    FULL_HD("1920x1200");

    private String resolution;

    BrowserSize(String resolution) {
        this.resolution = resolution;
    }

    public String getResolution() {
        return resolution;
    }
}
