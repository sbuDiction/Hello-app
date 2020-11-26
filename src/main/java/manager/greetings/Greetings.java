package manager.greetings;

import manager.database.DataBaseManagement;
import manager.exceptions.LanguageNotFoundException;
import manager.exceptions.UserAlreadyAddedException;
import manager.exceptions.UserNameNotFoundException;
import manager.languages.Languages;
import manager.utils.Person;

import java.net.URISyntaxException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sibusiso
 */

public class Greetings {
    private String greetingsMessage;
    public List<Person> personList = new ArrayList<>();
    public Person[] people = new Person[100];


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
                    new DataBaseManagement().insertPerson(new Person(username, timeStamp().toString(), count + 1, languageUpperCase));
                    setGreetingsMessage(languageType + username);
                }
            }
        } catch (LanguageNotFoundException languageNotFoundException) {
            setGreetingsMessage(languageNotFoundException.getMessage());
        } catch (UserAlreadyAddedException userAlreadyAddedException) {
            setGreetingsMessage(userAlreadyAddedException.getMessage());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public void getUserData(String userName) {
        try {
            String name = userName.substring(0, 1).toUpperCase() + userName.substring(1);
            List<Person> person = new DataBaseManagement().getPerson(name);
            person.forEach(user -> System.out.println(user.getFirstName() + "\n" + user.getTimeStamp() + "\n" + user.getLanguage()));


//        } catch (UserNameNotFoundException userNameNotFoundException) {
//            setGreetingsMessage(userNameNotFoundException.getMessage());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            System.out.println();
        }


    }

    public void getPeople() throws URISyntaxException {
        List<Person> people = new DataBaseManagement().getPeople();
    }

    private void setGreetingsMessage(String greetingsMessage) {
        this.greetingsMessage = greetingsMessage;
    }

    public String getGreetingsMessage() {
        return greetingsMessage;
    }

    public LocalTime timeStamp() {
        return LocalTime.now();
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