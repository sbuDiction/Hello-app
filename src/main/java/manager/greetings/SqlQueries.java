package manager.greetings;

public enum SqlQueries {
    INSERT_NAME_SQL("INSERT INTO GREETINGS (NAME, COUNT_TIME) VALUES (?, ?)"),
    FIND_NAME_SQL("SELECT * FROM GREETINGS WHERE NAME = ?"),
    UPDATE_USER_COUNTER("UPDATE GREETINGS SET COUNT_TIME = ? WHERE NAME = ?"),
    GET_NAMES("SELECT * FROM GREETINGS ORDER BY NAME OFFSET ?*3 ROWS FETCH NEXT 3 ROWS ONLY"),
    GET_COUNT("SELECT COUNT(*) AS COUNT FROM GREETINGS");

    private final String query;

    SqlQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
