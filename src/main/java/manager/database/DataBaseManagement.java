package manager.database;

import manager.utils.Person;
import mappers.PersonMapper;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.net.URISyntaxException;
import java.util.List;

public class DataBaseManagement implements PersonData {

    private final Jdbi jdbi = GreetingsDatabaseConnection.getJdbiDatabaseConnection();

    public DataBaseManagement() throws URISyntaxException {
        try {
            jdbi.installPlugin(new SqlObjectPlugin());
            jdbi.registerRowMapper(new PersonMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> getPeople() {
        return jdbi.withExtension(PersonData.class, PersonData::getPeople);
    }

    @Override
    public List<Person> getPerson(String firstName) {
        return jdbi.withExtension(PersonData.class, doa -> doa.getPerson(firstName));
    }

    @Override
    public void insertPerson(Person person) {
        jdbi.useExtension(PersonData.class, doa -> doa.insertPerson(person));
    }

}
