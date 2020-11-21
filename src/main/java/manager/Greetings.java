package manager;

import manager.exceptions.LanguageNotFoundException;
import manager.exceptions.UserAlreadyAddedException;
import manager.exceptions.UserNameNotFoundException;
import manager.languages.Languages;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sibusiso
 */

public class Greetings {
    private String greetingsMessage;

    public List<String> namesList = new ArrayList<>();

    public void greetUser(String firstName, String language) {
        try {
            String username = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            String languageUpperCase = language.toUpperCase();
            String languageType = Languages.languages.get(languageUpperCase);
            if (!username.isEmpty()) {
                if (languageType == null) {
                    throw new LanguageNotFoundException(languageUpperCase);
                } else {
                    addGreetedUserNames(username);

                    setGreetingsMessage(Languages.languages.get(languageUpperCase) + username);
                }
            }
        } catch (LanguageNotFoundException languageNotFoundException) {
            setGreetingsMessage(languageNotFoundException.getMessage());

        } catch (UserAlreadyAddedException userAlreadyAddedException) {
            setGreetingsMessage(userAlreadyAddedException.getMessage());
        }
    }

    private void addGreetedUserNames(String userName) throws UserAlreadyAddedException {
        if (namesList.contains(userName)) {
            throw new UserAlreadyAddedException(userName);
        }
        namesList.add(userName);
    }

    public void getUsername(String userName) {
        try {
            String name = userName.substring(0, 1).toUpperCase() + userName.substring(1);
            if (!namesList.contains(name)) {
                throw new UserNameNotFoundException(name);
            }
        } catch (UserNameNotFoundException userNameNotFoundException) {
            setGreetingsMessage(userNameNotFoundException.getMessage());
        }

    }

    private void setGreetingsMessage(String greetingsMessage) {
        this.greetingsMessage = greetingsMessage;
    }

    public String getGreetingsMessage() {
        return greetingsMessage;
    }
}