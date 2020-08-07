package greetings;

import languages.Language;

@FunctionalInterface
public interface Greetings {
    void greet(String name, Language language);
}
