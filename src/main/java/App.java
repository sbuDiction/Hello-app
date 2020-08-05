import manager.GreetManager;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import sun.rmi.transport.Connection;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    private static String greeting;
    private static ArrayList<String> users = new ArrayList<>();
    final static String KOANS_DATABASE_URL = "jdbc:h2:file:./target/greetings";


    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }


    public static void main(String[] args) {

        staticFiles.location("/public");
        GreetManager manager = new GreetManager();

        port(getHerokuAssignedPort());

        get("/hello", (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            return new ModelAndView(map, "hello.handlebars");
        }, new HandlebarsTemplateEngine());

        post("/hello", (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            manager.greeting(request.queryParams("username"), request.queryParams("language"));

            map.put("greeting", manager.getGreeting());
            map.put("users", manager.getUsers());
            map.put("counter", manager.getCount());
            return new ModelAndView(map, "hello.handlebars");
        }, new HandlebarsTemplateEngine());

        get("/greeted/:username", (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            manager.getEachUserCounter("Victor");
            System.out.println("Hello");
            return new ModelAndView(map, "greeted.handlebars");
        }, new HandlebarsTemplateEngine());


    }
}