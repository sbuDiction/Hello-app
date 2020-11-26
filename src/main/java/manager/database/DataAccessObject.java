package manager.database;


import manager.exceptions.UserAlreadyAddedException;
import manager.utils.Person;
import manager.mappers.PeopleMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

/**
 * @author Sibusiso Nkosi
 */

public interface DataAccessObject {
    @SqlQuery("select * from greetings")
    @RegisterBeanMapper(PeopleMapper.class)
    List<Person> getPeople();

    @SqlQuery("select * from greetings where first_name = :firstName")
    List<Person> getPerson(@Bind("firstName") String firstName);

    @SqlUpdate("insert into greetings (first_name, time_stamp, count_time, language_greeted_in) values (:firstName, :timeStamp, :count, :language)")
    void insertPerson(@BindBean Person person) throws UserAlreadyAddedException;
}
