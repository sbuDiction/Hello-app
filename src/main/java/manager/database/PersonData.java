package manager.database;


import manager.utils.Person;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface PersonData {
    @SqlQuery("select * from greetings")
    @RegisterBeanMapper(Person.class)
    List<Person> getPeople();

    @SqlQuery("select * from greetings where firstname = :firstname")
    @RegisterBeanMapper(Person.class)
    List<Person> getPerson();
}
