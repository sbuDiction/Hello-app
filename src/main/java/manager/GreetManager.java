package manager;

import manager.greetings.Greetings;
import manager.greetings.GreetingsDbMethods;
import manager.languages.Language;

import java.sql.*;

public abstract class GreetManager {
    private Connection connection;

    public GreetManager(Connection connection) {
        this.connection = connection;
    }

    public String greet(String name, Language language) throws SQLException {
        String username = name.substring(0, 1).toUpperCase() + name.substring(1);
        if (language.equals(new Greetings(username, language).getLanguage())) {
            new GreetingsDbMethods(getConnection()).checkNameDuplicate(username);
            return language.getPhrase() + username;
        } else {
            return "user already greeted counter updated!";
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
