package manager.utils;

public class Deserializer {
    private String userName;
    private String language;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUserName() {
        return userName;
    }

    public String getLanguage() {
        return language;
    }
}
