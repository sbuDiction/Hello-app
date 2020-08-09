package manager;

import languages.Language;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetManagerTest {

    public Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        String dbDiskURL = "jdbc:h2:file:./greetings_db_test";
        Jdbi jdbi = Jdbi.create(dbDiskURL, "sa", "");
        Handle handle = jdbi.open();
        handle.execute("create table if not exists greetings ( id integer identity, name text not null, count_time int )");
        return DriverManager.getConnection(dbDiskURL, "sa", "");
    }

    @BeforeEach
    public void cleanUpTables() {
        try {
            try (Connection conn = getConnection()) {
                Statement statement = conn.createStatement();
                statement.addBatch("delete from greetings");
                statement.executeBatch();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void greetingCounterTest() {
        try {
            cleanUpTables();
            GreetManager manager = new GreetManager(getConnection(), "zulu");
            manager.greet.greet("victor", Language.valueOf(manager.getLanguage()));
            manager.greet.greet("samuel", Language.valueOf(manager.getLanguage()));

            assertEquals(manager.getCount(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void greetingLanguageTest() {
        try {
            cleanUpTables();
            GreetManager manager = new GreetManager(getConnection(), "french");
            manager.greet.greet("Vusimuzi", Language.valueOf(manager.getLanguage()));

            assertEquals(manager.getGreeting(), "Bonjour, Vusimuzi");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void greetingNamesDuplicateTest() {
        try {
            cleanUpTables();
            GreetManager manager = new GreetManager(getConnection(), "english");
            manager.greet.greet("Vusimuzi", Language.valueOf(manager.getLanguage()));
            manager.greet.greet("Vusimuzi", Language.valueOf(manager.getLanguage()));
            manager.greet.greet("Vusimuzi", Language.valueOf(manager.getLanguage()));
            manager.greet.greet("Vusimuzi", Language.valueOf(manager.getLanguage()));
            List<String> names = manager.getUsers();

            assertEquals(manager.getUsers(), names);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void functionalInterfacesTest() {
        try {
            cleanUpTables();
            GreetManager manager = new GreetManager(getConnection(), "english");
            manager.greet.greet("sbusiso", Language.valueOf(manager.getLanguage()));
            assertEquals(manager.getGreeting(), "Hello, Sbusiso");
            assertEquals(manager.getCount(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getAllTheNamesGreeted() {
        try {
            cleanUpTables();
            GreetManager manager = new GreetManager(getConnection(), "sotho");

            manager.greet.greet("sbusiso", Language.valueOf(manager.getLanguage()));
            manager.greet.greet("thabiso", Language.valueOf(manager.getLanguage()));
            manager.greet.greet("jobe", Language.valueOf(manager.getLanguage()));
            manager.greet.greet("jobe", Language.valueOf(manager.getLanguage()));
            manager.greet.greet("khumalo", Language.valueOf(manager.getLanguage()));
            String list = Arrays.toString(manager.getNames().toArray());

            assertEquals(list, list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
