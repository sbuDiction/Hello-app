package manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetManagerTest {

    final String KOANS_DATABASE_URL = "jdbc:h2:file:./target/greetings";

    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(KOANS_DATABASE_URL, "sa", "");
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
    public void getDatabaseConnection() throws Exception {
        try {
            
            Class.forName("org.h2.Driver");
            cleanUpTables();
            GreetManager manager = new GreetManager(getConnection());
            manager.greeting("Victor", "english");

            assertEquals(manager.getGreeting(), "Hello, Victor");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
