package manager.languages;

public enum Language {
    ZULU("Sawubona, "),
    ENGLISH("Hello, "),
    XHOSA("Molo, "),
    RUSSIAN("Zdravstvuyte, "),
    FRENCH("Bonjour, "),
    JAPANESE("Konnichiwa, "),
    SWAHILI("Shikamoo, "),
    SOTHO("Dumela, "),
    CHINESE("Nǐn hǎo, "),
    NewLang("new lang, ");

    private final String phrase;

    Language(String phrase) {
        this.phrase = phrase;
    }

    public String getPhrase() {
        return phrase;
    }
}
