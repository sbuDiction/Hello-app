package manager.interfaces;

import manager.exceptions.NoUserNamesFoundException;
import manager.utils.Person;

import java.util.List;

@FunctionalInterface
public interface GetPeople {
    List<Person> get() throws NoUserNamesFoundException;
}
