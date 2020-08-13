package manager.greetings;

import manager.languages.Language;

public interface GreetingsInterface {

    String greet(String name, Language language);

    String addName(String name);
}
