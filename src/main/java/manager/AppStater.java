package manager;

import manager.greetings.GreetingsDbMethods;

import java.sql.Connection;

public class AppStater extends GreetingsDbMethods {
    public AppStater(Connection connection) {
        super(connection);
    }
}