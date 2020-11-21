package manager.exceptions;

public class UserNameNotFoundException extends GreetingsException {
    private final String message;

    public UserNameNotFoundException(String userName) {
        this.message = "Sorry username: " + userName +" does not exist.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
