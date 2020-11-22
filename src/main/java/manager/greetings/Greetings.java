package manager.greetings;

import manager.database.GreetingsDatabaseConnection;
import manager.exceptions.LanguageNotFoundException;
import manager.exceptions.UserAlreadyAddedException;
import manager.exceptions.UserNameNotFoundException;
import manager.languages.Languages;
import manager.utils.Person;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sibusiso
 */

public class Greetings {
    private String greetingsMessage;
    public List<Person> personList = new ArrayList<>();


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
                    addPerson(new Person(username, timeStamp().toString(), count + 1, languageUpperCase));
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
            for (Person persons : getPersonList()) {
                System.out.println(name.equals(persons.getFirstName()));
                System.out.println(persons);
                if (name.equals(persons.getFirstName())) {
                    System.out.println("User: " + persons.getFirstName() + "\n" + " was greeted at: " + persons.getTimeStamp() + "\nLanguage: " + persons.getLanguage());
                }
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

    public LocalTime timeStamp() {
        LocalTime localTime = LocalTime.now();
        return localTime;
    }

    public void addPerson(Person person) throws UserAlreadyAddedException {
        for (Person persons : personList) {
            if (persons.getFirstName().equals(person.getFirstName())) {
                throw new UserAlreadyAddedException(person.getFirstName());
            }
        }
        personList.add(person);
    }

    public List<Person> getPersonList() {
        return personList;
    }

}