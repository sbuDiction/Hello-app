package manager.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionsTest {
    @Test
    public void shouldThrowLanguageNotFoundExceptionTest() {
        manager.Greetings greetings = new manager.Greetings();
        greetings.greetUser("sibusiso", "lol");
        assertEquals("The language you selected does not exist: LOL", greetings.getGreetingsMessage());
    }
}
