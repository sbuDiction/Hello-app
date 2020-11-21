package manager.greetings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingsTest {

    @Test
    public void greetUserNameTest() {
        manager.Greetings greetings = new manager.Greetings();
        greetings.greetUser("sibusiso", "zulu");
        assertEquals("Sawubona, Sibusiso", greetings.getGreetingsMessage());
    }

    @Test
    public void greetUserNameInSothoTest() {
        manager.Greetings greetings = new manager.Greetings();
        greetings.greetUser("njabulo", "sotho");
        assertEquals("Dumela, Njabulo", greetings.getGreetingsMessage());
    }

}
