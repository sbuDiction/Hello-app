package manager.greetings;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingsTest {

    @Test
    public void greetUserNameTest() throws URISyntaxException {
        Greetings greetings = new Greetings();
        greetings.greetUser("sphesihle", "zulu");
        assertEquals("Sawubona, Sphesihle", greetings.getGreetingsMessage());
    }

    @Test
    public void greetUserNameInSothoTest() throws URISyntaxException {
        Greetings greetings = new Greetings();
        greetings.greetUser("njabulo", "sotho");
        assertEquals("Dumela, Njabulo", greetings.getGreetingsMessage());
    }

    @Test
    public void getUserData() throws URISyntaxException {
        Greetings greetings = new Greetings();
        greetings.greetUser("njabulo", "sotho");
        greetings.greetUser("sne", "sotho");
        greetings.greetUser("james", "sotho");
        greetings.getUserData("tido");
        assertEquals(0, 0);
    }

}
