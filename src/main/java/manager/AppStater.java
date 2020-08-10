package manager;

import manager.greetings.GreetingsDbMethods;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AppStater extends GreetingsDbMethods {
    public AppStater(Connection connection) {
        super(connection);
    }
}
