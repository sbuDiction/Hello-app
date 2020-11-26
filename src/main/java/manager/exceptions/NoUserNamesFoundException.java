package manager.exceptions;

public class NoUserNamesFoundException extends GreetingsException {
    private final String message;

    public NoUserNamesFoundException() {
        this.message = "Sorry no user names were found.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
