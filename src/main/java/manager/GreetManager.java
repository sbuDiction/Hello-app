package manager;

import manager.greetings.Greetings;
import manager.greetings.GreetingsInterface;
import manager.languages.Language;

import java.util.ArrayList;
import java.util.List;

public class GreetManager implements GreetingsInterface {
    private String greetings;

    @Override
    public String greet(String name, Language language) {
        String username = name.substring(0, 1).toUpperCase() + name.substring(1);
        Greetings greet = new Greetings(username, language);
        if (language.equals(greet.getLanguage())) {
            return language.getPhrase() + username;
        } else {
            return Language.JAPANESE.getPhrase() + username;
        }
    }

    @Override
    public String addName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public List<String> getLanguageList() {
        List<String> languageList = new ArrayList<>();
        Language[] langs = Language.values();

        for (Language language : langs
        ) {
            languageList.add(language.toString());
        }
        return languageList;
    }

    public String getGreetings() {
        return greetings;
    }


}
