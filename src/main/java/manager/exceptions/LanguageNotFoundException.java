package manager.exceptions;


public class LanguageNotFoundException extends GreetingsException {
    private final String message;

    public LanguageNotFoundException(String language) {
        this.message = "The language you selected does not exist: " + language;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
