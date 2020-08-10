package manager;

import jsonserialization.Serializer;
import manager.greetings.Greetings;
import manager.greetings.GreetingsDbMethods;
import manager.greetings.GreetingsInterface;
import manager.languages.Language;
import manager.languages.LanguageJson;

import java.util.ArrayList;
import java.util.List;

public class GreetManager implements GreetingsInterface {

    @Override
    public String greet(String name, Language language) {
        String username = name.substring(0, 1).toUpperCase() + name.substring(1);
        try {
            if (language.equals(new Greetings(username, language).getLanguage())) {
                addName(username);
                return language.getPhrase() + username;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Language.CHINESE.getPhrase() + username;
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
            languageList.add(new Serializer().fromObjectToJson(new LanguageJson(language.toString())));
        }
        return languageList;
    }
}
