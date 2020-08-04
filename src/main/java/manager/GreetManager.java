package manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GreetManager {
    private String greeting;
    private static ArrayList<String> users = new ArrayList<>();

    public Boolean greeting(String name, String language) {
        if (!users.contains(name)) {
            if (language.equals("english")) {
                greeting = "Hello, " + name;
            }
            if (language.equals("zulu")) {
                greeting = "Sawubona, " + name;
            }
            if (language.equals("xhosa")) {
                greeting = "Molo, " + name;
            }
            users.add(name);
        } else {
            greeting = "user already greeted";
            users.add(name);
            return true;
        }
        return true;
    }

    public String getGreeting() {
        return greeting;
    }

    public int getCount() {
        return users.size();
    }

    public ArrayList<String> getUsers() {
        System.out.println("______________________");
        users.stream().distinct().forEach(System.out::println);
        System.out.println("__________________________");
//        System.out.println(users.stream().max());
        return users;
    }

    public int getEachUserCounter(String name) {
        int counter = 0;
//        for (int i = 0; i < users.size(); i++) {
//            System.out.println(users.get(i));
//            if (name.equals())
//        }
//        if (users.contains(name)) {
//            Stream<String> unique = users.stream().distinct();
//            System.out.println(unique + " user");
//        }
        return counter;
    }
}
