package manager.utils;

/**
 * @author Sibusiso
 */

public class Person {
    private String firstName;
    private String timeStamp;
    private int count;
    private String language;

    public Person(String firstName, String timeStamp, int count, String language) {
        this.firstName = firstName;
        this.timeStamp = timeStamp;
        this.count = count;
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
}
