package manager;

import manager.greetings.Greetings;
import manager.greetings.GreetingsDbMethods;
import manager.greetings.People;
import manager.languages.Language;
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
            GreetManager manager = new AppStater(getConnection());
            manager.greet("samuel", new Greetings("samuel", Language.valueOf("ZULU")).getLanguage());
            manager.greet("james", new Greetings("james", Language.valueOf("ZULU")).getLanguage());

            assertEquals(new GreetingsDbMethods(getConnection()).getCount(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void greetingLanguageTest() {
        try {
            cleanUpTables();
            GreetManager manager = new AppStater(getConnection());
            String greetings = manager.greet("Vusimuzi", new Greetings("Vusimuzi", Language.valueOf("FRENCH")).getLanguage());
            System.out.println(greetings);
            assertEquals(greetings, "Bonjour, Vusimuzi");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //
    @Test
    public void greetingNamesDuplicateTest() {
        try {
            cleanUpTables();
            GreetManager manager = new AppStater(getConnection());
            String greetings = manager.greet("Vusimuzi", new Greetings("Vusimuzi", Language.valueOf("FRENCH")).getLanguage());
            manager.greet("Vusimuzi", new Greetings("Vusimuzi", Language.valueOf("FRENCH")).getLanguage());
            manager.greet("Vusimuzi", new Greetings("Vusimuzi", Language.valueOf("FRENCH")).getLanguage());
            manager.greet("Vusimuzi", new Greetings("Vusimuzi", Language.valueOf("FRENCH")).getLanguage());
            GreetingsDbMethods methods = new GreetingsDbMethods(getConnection());
            List<String> list = methods.getNames();
            assertEquals(methods.getNames(), list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllTheNamesGreeted() {
        try {
            cleanUpTables();
            GreetManager manager = new AppStater(getConnection());
            manager.greet("Vusimuzi", new Greetings("Vusimuzi", Language.valueOf("FRENCH")).getLanguage());
            manager.greet("sbusiso", new Greetings("sbusiso", Language.valueOf("ENGLISH")).getLanguage());
            manager.greet("thabiso", new Greetings("thabiso", Language.valueOf("XHOSA")).getLanguage());
            manager.greet("jobe", new Greetings("jobe", Language.valueOf("SOTHO")).getLanguage());
            manager.greet("khumalo", new Greetings("khumalo", Language.valueOf("ZULU")).getLanguage());
            List<String> list = new GreetingsDbMethods(getConnection()).getNames();
            System.out.println(list);
            assertEquals(new GreetingsDbMethods(getConnection()).getNames(), list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
