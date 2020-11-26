package manager.interfaces;

import manager.exceptions.UserNameNotFoundException;
import manager.utils.Person;

import java.util.List;

@FunctionalInterface
public interface GetPerson {
    List<Person> get(String firstName) throws UserNameNotFoundException;

}
