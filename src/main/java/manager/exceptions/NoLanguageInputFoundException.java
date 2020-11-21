package manager.exceptions;

public class NoLanguageInputFoundException extends GreetingsException {
    private final String message;

    public NoLanguageInputFoundException() {
        this.message = "Please select a language.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
