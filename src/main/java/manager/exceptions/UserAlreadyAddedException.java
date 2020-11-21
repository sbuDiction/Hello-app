package manager.exceptions;

public class UserAlreadyAddedException extends GreetingsException {
    private final String message;

    public UserAlreadyAddedException(String userName) {
        this.message = "User: " + userName + " has already been added.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
