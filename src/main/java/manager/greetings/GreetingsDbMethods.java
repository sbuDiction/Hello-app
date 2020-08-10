package manager.greetings;

import jsonserialization.Serializer;
import manager.languages.Language;
import manager.languages.LanguageJson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GreetingsDbMethods {
    private Connection connection;

    public GreetingsDbMethods(Connection connection) {
        this.connection = connection;
    }

    public Boolean checkNameDuplicate(String name) throws SQLException {
        PreparedStatement find_user = connection.prepareStatement(String.valueOf(SqlQueries.FIND_NAME_SQL.getQuery()));
        find_user.setString(1, name);
        ResultSet resultSet = find_user.executeQuery();
        if (resultSet.next()) {
            PreparedStatement update_user_counter = connection.prepareStatement(String.valueOf(SqlQueries.UPDATE_USER_COUNTER.getQuery()));
            update_user_counter.setInt(1, resultSet.getInt("count_time") + 1);
            update_user_counter.setString(2, name);
            update_user_counter.execute();
            return true;
        } else {
            PreparedStatement add_user = connection.prepareStatement(String.valueOf(SqlQueries.INSERT_NAME_SQL.getQuery()));
            add_user.setString(1, name);
            add_user.setInt(2, 1);
            add_user.execute();
            return false;
        }
    }

    public List<String> getNames() throws SQLException {
        List<String> greetedList = new ArrayList<String>();
        PreparedStatement get_all_the_content = connection.prepareStatement(String.valueOf(SqlQueries.GET_NAMES.getQuery()));
        ResultSet resultSet = get_all_the_content.executeQuery();
        while (resultSet.next()) {
            greetedList.add(new Serializer()
                    .fromObjectToJson(new People(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("count_time"))));
        }
        return greetedList;
    }

    public List<String> getLanguageList() {
        List<String> languageList = new ArrayList<>();
        Language[] langs = Language.values();

        for (Language language : langs
        ) {
            languageList.add(new Serializer().fromObjectToJson(new LanguageJson(language.toString())));
        }
        return languageList;
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
}
