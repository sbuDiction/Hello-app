package jsonserialization;

import languages.Language;

public class Deserializer extends Serializer {
    private String userName;
    private String language;

    public void setUserName(String userName) {
        System.out.println(userName);
        this.userName = userName;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUserName() {
        System.out.println(userName);
        return userName;
    }

    public String getLanguage() {
        return language;
    }
}
