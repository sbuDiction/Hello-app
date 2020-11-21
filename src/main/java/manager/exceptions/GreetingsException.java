package manager.exceptions;

public class GreetingsException extends Exception {
    private final String message;

    public GreetingsException() {
        this.message = "Something went wrong with the greetings program.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
