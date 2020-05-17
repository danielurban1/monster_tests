package enums;

public enum SavedJobsPageWordings {
    AMOUNT_OF_SAVED_JOBS("You have saved STRING of 25 possible jobs.");

    private String wordings;

    SavedJobsPageWordings(String wordings) {
        this.wordings = wordings;
    }

    public String getWording(int amountOfSavedJobs) {

        return wordings.replace("STRING", String.valueOf(amountOfSavedJobs));
    }
}
