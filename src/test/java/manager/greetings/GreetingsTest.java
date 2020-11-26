package manager.greetings;

import manager.exceptions.UserNameNotFoundException;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingsTest {

    @Test
    public void greetUserNameTest() throws URISyntaxException {
        Greetings greetings = new Greetings();
        greetings.greet.greetUser("sphesihle", "zulu");
        assertEquals("Sawubona, Sphesihle", greetings.getGreetingsMessage());
    }

    @Test
    public void greetUserNameInSothoTest() throws URISyntaxException {
        Greetings greetings = new Greetings();
        greetings.greet.greetUser("njabulo", "sotho");
        assertEquals("Dumela, Njabulo", greetings.getGreetingsMessage());
    }

    @Test
    public void getUserData() throws URISyntaxException, UserNameNotFoundException {
        Greetings greetings = new Greetings();
        greetings.greet.greetUser("njabulo", "sotho");
        greetings.greet.greetUser("sne", "sotho");
        greetings.greet.greetUser("james", "sotho");
        greetings.getPerson.get("tido");
        assertEquals(0, 0);
    }

}
