package manager;


import database.DbManagement;
import greetings.Greetings;
import greetings.People;
import jsonserialization.Serializer;
import languages.Language;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GreetManager implements DbManagement {
    private String greeting;
    private Connection connection;
    private String language;
    private String username;
    private String languageUpperCase;
    List<String> namesList = new ArrayList<>();

    private final String INSERT_NAME_SQL = "INSERT INTO GREETINGS (NAME, COUNT_TIME) VALUES (?, ?)";
    private final String FIND_NAME_SQL = "SELECT * FROM GREETINGS WHERE NAME = ?";
    private final String UPDATE_USER_COUNTER = "UPDATE GREETINGS SET COUNT_TIME = ? WHERE NAME = ?";
    private final String GET_DB_CONTENT = "SELECT * FROM GREETINGS ORDER BY NAME";

    public GreetManager(Connection connection, String language) {
        this.connection = connection;
        this.language = language;
    }

    public Greetings greet = (name, language) -> {
        System.out.println(name + " " + language);
        username = name.substring(0, 1).toUpperCase() + name.substring(1);
        languageUpperCase = language.toString().toUpperCase();
        if (greetDbManagement(name).equals(false) && languageUpperCase.equals(getLanguage())) {
            greeting = language.getPhrase() + username;
        } else {
            greeting = "user already greeted counter updated!";
        }
    };

    public String getGreeting() {
        return greeting;
    }

    public Integer getCount() {
        try {
            PreparedStatement statement = connection.prepareStatement("select count(*) as count from greetings");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            } else {
                return 0;
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return 0;
        }
    }

    public List<String> getUsers() {
        try {
            PreparedStatement get_all_the_content = connection.prepareStatement(GET_DB_CONTENT);
            ResultSet resultSet = get_all_the_content.executeQuery();
            while (resultSet.next()) {
                namesList.add(resultSet.getString("name"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return namesList;
    }

    public String getLanguage() {
        String languageUpperCase = language.toUpperCase();
        return languageUpperCase;
    }

    public List<String> getNames() throws SQLException {
        List<String> greetedList = new ArrayList<String>();
        PreparedStatement get_all_the_content = connection.prepareStatement(GET_DB_CONTENT);
        ResultSet resultSet = get_all_the_content.executeQuery();
        while (resultSet.next()) {
            greetedList.add(new Serializer()
                    .fromObjectToJson(new People(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("count_time"))));
        }
        return greetedList;
    }

    @Override
    public Boolean greetDbManagement(String name) throws SQLException {
        System.out.println(name);
        PreparedStatement find_user = connection.prepareStatement(FIND_NAME_SQL);
        find_user.setString(1, name);
        ResultSet resultSet = find_user.executeQuery();
        if (resultSet.next()) {
            PreparedStatement update_user_counter = connection.prepareStatement(UPDATE_USER_COUNTER);
            update_user_counter.setInt(1, resultSet.getInt("count_time") + 1);
            update_user_counter.setString(2, name);
            update_user_counter.execute();
            return true;
        } else {
            PreparedStatement add_user = connection.prepareStatement(INSERT_NAME_SQL);
            add_user.setString(1, name);
            add_user.setInt(2, 1);
            add_user.execute();
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public String setLanguageUpperCase() {
        return languageUpperCase;
    }

    public String getUsername() {
        return username;
    }

    public String getLanguageList() {
        Language[] list = Language.values();
//        String json = new Serializer().fromObjectToJson();
        return Arrays.toString(list);
    }
}
