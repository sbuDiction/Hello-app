package manager;

import java.util.ArrayList;

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
            return false;
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
        return users;
    }

    public int getEachUserCounter(String name) {
        int counter = 0;
        return counter;
    }
}
