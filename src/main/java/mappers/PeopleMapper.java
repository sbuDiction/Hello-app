package mappers;

import java.beans.ConstructorProperties;

public class PeopleMapper {
    private String firstName;
    private String timeStamp;
    private int count;
    private String language;

    public PeopleMapper() {
        super();
    }

    @ConstructorProperties({"firstName", "timeStamp", "count", "language"})
    public PeopleMapper(String firstName, String timeStamp, int count, String language) {
        this.firstName = firstName;
        this.timeStamp = timeStamp;
        this.count = count;
        this.language = language;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public int getCount() {
        return count;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return getFirstName();
    }
}
