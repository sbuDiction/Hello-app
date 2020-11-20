package manager.exceptions;

public class GreetingsException extends Exception {
    private String message;

    public GreetingsException() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
