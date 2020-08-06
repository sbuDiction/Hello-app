package manager;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GreetManager {
    private String greeting;
    private static ArrayList<String> users = new ArrayList<>();
    private final Connection connection;
    private final String INSERT_NAME_SQL = "INSERT INTO GREETINGS (NAME, COUNT_TIME) VALUES (?, ?)";
    private final String FIND_NAME_SQL = "SELECT * FROM GREETINGS WHERE NAME = ?";
    private final String UPDATE_USER_COUNTER = "UPDATE GREETINGS SET COUNT_TIME = ? WHERE NAME = ?";
    private final String GET_DB_CONTENT = "SELECT * FROM GREETINGS ORDER BY NAME";

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
                greeting = "user already greeted";
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

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

    public List<String> getUsers() throws SQLException {
        List<String> stringList = new ArrayList<>();
        try {
            PreparedStatement get_all_the_content = connection.prepareStatement(GET_DB_CONTENT);
            ResultSet resultSet = get_all_the_content.executeQuery();
            while (resultSet.next()) {
                stringList.add(resultSet.getString("name"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return stringList;
    }

    public int getEachUserCounter(String name) {
        int counter = 0;
        return counter;
    }
}
