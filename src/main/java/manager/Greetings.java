package manager;

import manager.exceptions.LanguageNotFoundException;
import manager.languages.Languages;

/**
 * @author Sibusiso
 */

public class Greetings {
    private String greetingsMessage;

    public void greetUser(String firstName, String language) {
        try {
            String username = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            String languageUpperCase = language.toUpperCase();
            String languageType = Languages.languages.get(languageUpperCase);
            if (!username.isEmpty()) {
                if (languageType == null) {
                    throw new LanguageNotFoundException(languageUpperCase);
                } else {
                    setGreetingsMessage(Languages.languages.get(languageUpperCase) + username);
                }
            }
        } catch (LanguageNotFoundException languageNotFoundException) {
            setGreetingsMessage(languageNotFoundException.getMessage());
        }
    }

    private void setGreetingsMessage(String greetingsMessage) {
        this.greetingsMessage = greetingsMessage;
    }

    public String getGreetingsMessage() {
        return greetingsMessage;
    }
}
