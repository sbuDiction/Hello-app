package manager.exceptions;

import manager.greetings.Greetings;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionsTest {
    @Test
    public void shouldThrowLanguageNotFoundExceptionTest() throws URISyntaxException {
        Greetings greetings = new Greetings();
        greetings.greetUser("james", "lol");
        assertEquals("The language you selected does not exist: LOL", greetings.getGreetingsMessage());
    }

    @Test
    public void shouldThrowUserAlreadyAddedFoundExceptionTest() throws URISyntaxException {
        Greetings greetings = new Greetings();
        greetings.greetUser("sibusiso", "english");
        greetings.greetUser("sibusiso", "english");
        assertEquals("User: Sibusiso has already been added.", greetings.getGreetingsMessage());
    }

    @Test
    public void shouldThrowUserNameNotFoundExceptionTest() throws URISyntaxException {
        Greetings greetings = new Greetings();
        greetings.greetUser("thabiso", "english");
        greetings.greetUser("vusi", "english");
        greetings.getUserData("lindani");
        assertEquals("Sorry username: Lindani does not exist.", greetings.getGreetingsMessage());
    }
}
