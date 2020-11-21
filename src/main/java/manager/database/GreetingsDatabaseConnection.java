package manager.database;

import manager.exceptions.UserAlreadyAddedException;
import manager.utils.Person;

import java.util.ArrayList;
import java.util.List;

public class GreetingsDatabaseConnection {
    public static List<Person> personList = new ArrayList<>();

    public static void addPerson(Person person) throws UserAlreadyAddedException {
        for (Person persons : personList) {
            if (persons.getFirstName().equals(person.getFirstName())) {
                throw new UserAlreadyAddedException(person.getFirstName());
            }
            break;
        }
        personList.add(person);
    }

    public static List<Person> getPersonList() {
        return personList;
    }
}
