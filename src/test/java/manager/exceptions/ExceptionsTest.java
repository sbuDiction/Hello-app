package manager.exceptions;

import manager.greetings.Greetings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionsTest {
    @Test
    public void shouldThrowLanguageNotFoundExceptionTest() {
        Greetings greetings = new Greetings();
        greetings.greetUser("sibusiso", "lol");
        assertEquals("The language you selected does not exist: LOL", greetings.getGreetingsMessage());
    }

    @Test
    public void shouldThrowUserAlreadyAddedFoundExceptionTest() {
        Greetings greetings = new Greetings();
        greetings.greetUser("sibusiso", "english");
        greetings.greetUser("sibusiso", "english");
        assertEquals("User: Sibusiso has already been added.", greetings.getGreetingsMessage());
    }

    @Test
    public void shouldThrowUserNameNotFoundExceptionTest() {
        Greetings greetings = new Greetings();
        greetings.greetUser("sibusiso", "english");
        greetings.greetUser("vusi", "english");
        greetings.getUserData("lindani");
        assertEquals("Sorry username: Lindani does not exist.", greetings.getGreetingsMessage());
    }
}
