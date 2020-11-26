package manager.interfaces;

@FunctionalInterface
public interface GreetingsInterface {
    void greetUser(String firstName, String language);

    static String setUserNameFirstLetterToUpperCase(String firstName){
        return firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
    }
}