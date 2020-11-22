package manager.greetings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingsTest {

    @Test
    public void greetUserNameTest() {
        Greetings greetings = new Greetings();
        greetings.greetUser("sphesihle", "zulu");
        assertEquals("Sawubona, Sphesihle", greetings.getGreetingsMessage());
    }

    @Test
    public void greetUserNameInSothoTest() {
        Greetings greetings = new Greetings();
        greetings.greetUser("njabulo", "sotho");
        assertEquals("Dumela, Njabulo", greetings.getGreetingsMessage());
    }

}
