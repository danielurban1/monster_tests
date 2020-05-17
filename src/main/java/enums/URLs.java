package enums;

public enum URLs {
    MONSTER_HOME_PAGE_URL("https://www.monsterworksdemo.com/home/"),
    MONSTER_DASHBOARD_PAGE_URL("https://www.monsterworksdemo.com/dashboard/"),
    MONSTER_ACCOUNT_LITE_PAGE_URL("https://www.monsterworksdemo.com/account/account-lite"),
    MONSTER_SAVED_JOBS_URL("https://www.monsterworksdemo.com/savedJobs/"),
    BROWSE_MONSTER_SEARCH_URL("https://browse.monsterworksdemo.com/search/?cn="),
    SERVICES_SEEKER_API_ME_URL("https://services.lexus.monster.com/seeker/api/me");

    private String url;

    URLs(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
