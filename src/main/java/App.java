import manager.GreetManager;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    final static String KOANS_DATABASE_URL = "jdbc:h2:file:./target/greetings";

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static Connection getConnectionFromDb() throws Exception {
        return DriverManager.getConnection(KOANS_DATABASE_URL, "sa", "");
    }


    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");

            staticFiles.location("/public");
            GreetManager manager = new GreetManager(getConnectionFromDb());
            port(getHerokuAssignedPort());

            get("/hello", (request, response) -> {
                Map<String, Object> map = new HashMap<>();
                map.put("users", manager.getUsers());
                map.put("counter", manager.getCount());
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
//                manager.getEachUserCounter("Victor");
                return new ModelAndView(map, "greeted.handlebars");
            }, new HandlebarsTemplateEngine());

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }


    }
}
