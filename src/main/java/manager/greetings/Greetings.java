package manager.greetings;

import manager.database.GreetingsDatabaseConnection;
import manager.exceptions.LanguageNotFoundException;
import manager.exceptions.UserAlreadyAddedException;
import manager.exceptions.UserNameNotFoundException;
import manager.languages.Languages;
import manager.utils.Person;

import java.time.LocalTime;

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
            int count = 0;
            if (!username.isEmpty()) {
                if (languageType == null) {
                    throw new LanguageNotFoundException(languageUpperCase);
                } else {
                    GreetingsDatabaseConnection.addPerson(new Person(
                            username,
                            timeStamp().toString(),
                            count + 1,
                            languageUpperCase));
                    setGreetingsMessage(languageType + username);
                }
            }
        } catch (LanguageNotFoundException languageNotFoundException) {
            setGreetingsMessage(languageNotFoundException.getMessage());

        } catch (UserAlreadyAddedException userAlreadyAddedException) {
            setGreetingsMessage(userAlreadyAddedException.getMessage());
        }
    }


    public void getUserData(String userName) {
        try {
            String name = userName.substring(0, 1).toUpperCase() + userName.substring(1);
            for (Person persons : GreetingsDatabaseConnection.getPersonList()) {
                System.out.println(persons.getFirstName());
                if (!name.equals(persons.getFirstName())) {
                    throw new UserNameNotFoundException(name);
                } else {
                    System.out.println(persons.getFirstName() + "\n" + persons.getTimeStamp());
                }
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

    public LocalTime timeStamp() {
        LocalTime localTime = LocalTime.now();
        return localTime;
    }

    public static void main(String[] args) {
        
    }
}