package manager.exceptions;

import manager.greetings.Greetings;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionsTest {
    @Test
    public void shouldThrowLanguageNotFoundExceptionTest() throws URISyntaxException {
        Greetings greetings = new Greetings();
        greetings.greet.greetUser("james", "lol");
        assertEquals("The language you selected does not exist: LOL", greetings.getGreetingsMessage());
    }

    @Test
    public void shouldThrowUserAlreadyAddedFoundExceptionTest() throws URISyntaxException {
        Greetings greetings = new Greetings();
        greetings.greet.greetUser("sibusiso", "english");
        greetings.greet.greetUser("sibusiso", "english");
        assertEquals("User: Sibusiso has already been added.", greetings.getGreetingsMessage());
    }

    @Test
    public void shouldThrowUserNameNotFoundExceptionTest() throws UserNameNotFoundException {
        Greetings greetings = new Greetings();
        greetings.greet.greetUser("thabiso", "english");
        greetings.greet.greetUser("vusi", "english");
        greetings.getPerson.get("lindani");
        assertEquals("Sorry username: Lindani does not exist.", greetings.getGreetingsMessage());
    }
}
