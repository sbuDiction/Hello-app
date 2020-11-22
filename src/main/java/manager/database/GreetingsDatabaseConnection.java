package manager.database;

import manager.exceptions.UserAlreadyAddedException;
import manager.utils.Person;
import org.jdbi.v3.core.Jdbi;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sibusiso
 */

public class GreetingsDatabaseConnection {
    public static List<Person> personList = new ArrayList<>();

    public static void addPerson(Person person) throws UserAlreadyAddedException {
        for (Person persons : personList) {
            if (persons.getFirstName().equals(person.getFirstName())) {
                throw new UserAlreadyAddedException(person.getFirstName());
            }
        }
        personList.add(person);
    }

    public static List<Person> getPersonList() {
        return personList;
    }

    static Jdbi getJdbiDatabaseConnection() throws URISyntaxException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String database_url = processBuilder.environment().get("DATABASE_URL");
        if (database_url != null) {

            URI uri = new URI(database_url);
            String[] hostParts = uri.getUserInfo().split(":");
            String username = hostParts[0];
            String password = hostParts[1];
            String host = uri.getHost();

            int port = uri.getPort();

            String path = uri.getPath();
            String url = String.format("jdbc:postgresql://%s:%s%s", host, port, path);

            return Jdbi.create(url, username, password);
        }
        return Jdbi.create("jdbc:postgresql://localhost/taxi_fare?user=slacker&password=1234");
    }

    public static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

}
