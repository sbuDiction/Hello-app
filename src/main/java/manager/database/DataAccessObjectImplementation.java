package manager.database;

import manager.exceptions.UserAlreadyAddedException;
import manager.utils.Person;
import manager.mappers.PersonMapper;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Sibusiso Nkosi
 */

public class DataAccessObjectImplementation implements DataAccessObject {

    private final Jdbi jdbi = GreetingsDatabaseConnection.getJdbiDatabaseConnection();

    public DataAccessObjectImplementation() throws URISyntaxException {
        try {
            jdbi.installPlugin(new SqlObjectPlugin());
            jdbi.registerRowMapper(new PersonMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> getPeople() {
        return jdbi.withExtension(DataAccessObject.class, DataAccessObject::getPeople);
    }

    @Override
    public List<Person> getPerson(String firstName) {
        return jdbi.withExtension(DataAccessObject.class, doa -> doa.getPerson(firstName));
    }

    @Override
    public void insertPerson(Person person) throws UserAlreadyAddedException {
        jdbi.useExtension(DataAccessObject.class, dao -> {
            String firstName = person.getFirstName();
            if (dao.getPerson(firstName).size() == 1) {
                throw new UserAlreadyAddedException(firstName);
            } else {
                dao.insertPerson(person);
            }
        });
    }

}
