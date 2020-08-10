package manager.greetings;

import manager.languages.Language;

public class Greetings {
    private String name;
    private Language language;

    public Greetings(String name, Language language) {
        this.name = name;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public Language getLanguage() {
        return language;
    }
}
