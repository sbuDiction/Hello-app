package greetings;

import languages.Language;

import java.sql.SQLException;

@FunctionalInterface
public interface Greetings {
    void greet(String name, Language language) throws SQLException;
}
