package enums;

public enum GlobalTimeouts {
    MAX_PRESENCE_TIMEOUT_MILLIS(800000),
    MAX_VISIBILITY_TIMEOUT_MILLIS(4000),
    MAX_INTERACTIVITY_TIMEOUT_MILLIS(4000),
    DRIVER_TIMEOUT_MILLIS(5000);

    private int timeout;

    GlobalTimeouts(int timeout) {
        this.timeout = timeout;
    }

    public int getTimeout() {
        return timeout;
    }
}
