package manager;

import languages.Language;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetManagerTest {

    public Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        String dbDiskURL = "jdbc:h2:file:./greetings_db";
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
            manager.greeting("Victor", "english");
            manager.greeting("Sibusiso", "zulu");

            assertEquals(manager.getCount(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void greetingLanguageTest() {
        try {
            cleanUpTables();
            GreetManager manager = new GreetManager(getConnection(), "xhosa");
            manager.greeting("Vusimuzi", "xhosa");

            assertEquals(manager.getGreeting(), "Molo, Vusimuzi");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void greetingNamesDuplicateTest() {
        try {
            cleanUpTables();
            GreetManager manager = new GreetManager(getConnection(), "english");
            manager.greeting("Vusimuzi", "xhosa");
            manager.greeting("Vusimuzi", "english");
            manager.greeting("Vusimuzi", "xhosa");
            manager.greeting("Vusimuzi", "zulu");
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
}
