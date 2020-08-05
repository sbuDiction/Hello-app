package manager;

import java.sql.*;
import java.util.ArrayList;

public class GreetManager {
    private String greeting;
    private static ArrayList<String> users = new ArrayList<>();
    private final Connection connection;
    private final String INSERT_NAME_SQL = "INSERT INTO GREETINGS (NAME, COUNT_TIME) VALUES (?, ?)";
    private final String FIND_NAME_SQL = "SELECT * FROM GREETINGS WHERE NAME = ?";
    private final String UPDATE_USER_COUNTER = "UPDATE GREETINGS SET COUNT_TIME = ? WHERE NAME = ?";
    private final String GET_DB_CONTENT = "SELECT * FROM GREETINGS";
    private final String COUNTER = "SELECT COUNT(*) AS USER_COUNTER";

    public GreetManager(Connection connection) {
        this.connection = connection;
    }

    public void greeting(String name, String language) {
        try {
            PreparedStatement find_user = connection.prepareStatement(FIND_NAME_SQL);
            find_user.setString(1, name);
            ResultSet resultSet = find_user.executeQuery();
            if (resultSet.next()) {
                PreparedStatement update_user_counter = connection.prepareStatement(UPDATE_USER_COUNTER);
                update_user_counter.setInt(1, resultSet.getInt("count_time") + 1);
                update_user_counter.setString(2, name);
                update_user_counter.execute();
            } else {
                if (language.equals("english")) {
                    greeting = "Hello, " + name;
                }
                if (language.equals("zulu")) {
                    greeting = "Sawubona, " + name;
                }
                if (language.equals("xhosa")) {
                    greeting = "Molo, " + name;
                }
                PreparedStatement add_user = connection.prepareStatement(INSERT_NAME_SQL);
                add_user.setString(1, name);
                add_user.setInt(2, 10);
                add_user.execute();
                greeting = "user already greeted";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public String getGreeting() {
        return greeting;
    }

    public int getCount() throws SQLException {
        PreparedStatement count = connection.prepareStatement(COUNTER);
        ResultSet resultSet = count.executeQuery();
        return resultSet.getInt("USER_COUNT");
    }

    public String getUsers() throws SQLException {
        CallableStatement get_all_the_content = connection.prepareCall(GET_DB_CONTENT);
        return get_all_the_content.getString("NAME");
    }

    public int getEachUserCounter(String name) {
        int counter = 0;
        return counter;
    }
}
