package manager.greetings;

import manager.database.DataAccessObjectImplementation;
import manager.exceptions.*;
import manager.interfaces.GetPeople;
import manager.interfaces.GetPerson;
import manager.interfaces.GreetingsInterface;
import manager.languages.Languages;
import manager.utils.Person;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Sibusiso Nkosi
 */

public class Greetings {
    private String greetingsMessage;

    public Greetings() {
    }

    public GreetingsInterface greet = (name, language) -> {
        try {
            String username = GreetingsInterface.setUserNameFirstLetterToUpperCase(name);
            String getLanguage = Languages.languages.get(language.toUpperCase());
            int count = 0;
            if (!username.isEmpty()) {
                if (getLanguage == null) {
                    throw new LanguageNotFoundException(language.toUpperCase());
                } else {
                    new DataAccessObjectImplementation().insertPerson(new Person(username, timeStamp().toString(), count + 1, language.toUpperCase()));
                    setGreetingsMessage(getLanguage + username);
                }
            }
        } catch (GreetingsException | URISyntaxException exception) {
            setGreetingsMessage(exception.getMessage());
            setGreetingsMessage(exception.getMessage());
        }
    };

    public GetPerson getPerson = (firstName -> {
        try {
            String userName = GreetingsInterface.setUserNameFirstLetterToUpperCase(firstName);
            List<Person> person = new DataAccessObjectImplementation().getPerson(userName);
            if (person.size() != 0) {
                DateTimeFormatter d1 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                person.forEach(user -> System.out.println(user.getFirstName() + "\n" + LocalDateTime.parse(user.getTimeStamp()).format(d1) + "\n" + user.getLanguage()));
                return person;
            }
            throw new UserNameNotFoundException(userName);

        } catch (GreetingsException | URISyntaxException exception) {
            setGreetingsMessage(exception.getMessage());
        }
        return null;
    });

    public GetPeople getPeople = (() -> {
        try {
            List<Person> people = new DataAccessObjectImplementation().getPeople();
            if (people.size() == 0) {
                throw new NoUserNamesFoundException();
            }
            return people;
        } catch (GreetingsException | URISyntaxException exception) {
            setGreetingsMessage(exception.getMessage());
        }
        return null;
    });

    private void setGreetingsMessage(String greetingsMessage) {
        this.greetingsMessage = greetingsMessage;
    }

    public String getGreetingsMessage() {
        return greetingsMessage;
    }

    public LocalDateTime timeStamp() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        return localDateTime;
    }

}