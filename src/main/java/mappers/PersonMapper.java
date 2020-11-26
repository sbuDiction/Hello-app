package mappers;

import manager.utils.Person;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person map(ResultSet rs, StatementContext stx) throws SQLException {
        return new Person(rs.getString("first_name"), rs.getString("time_stamp"), rs.getInt("count_time"), rs.getString("language_greeted_in"));
    }
}
