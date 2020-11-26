import manager.exceptions.GreetingsException;
import manager.exceptions.UserNameNotFoundException;
import manager.greetings.Greetings;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws GreetingsException {
        Greetings greetings = new Greetings();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String[] input = scanner.nextLine().split(" ");
            if (input[0].equals("greet")) {
                greetings.greet.greetUser(input[1], input[2]);
                System.out.println(greetings.getGreetingsMessage());
            }
            if (input[0].equals("get")) {
                greetings.getPerson.get(input[1]);
                System.out.println(greetings.getGreetingsMessage());

            }

            if (input[0].equals("all")) {
                System.out.println(greetings.getPeople.get());
                System.out.println(greetings.getGreetingsMessage());
            }
        }
    }
}