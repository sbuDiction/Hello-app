import manager.GreetManager;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    private static String greeting;
    private static ArrayList<String> users = new ArrayList<>();


    public static void main(String[] args) {

        staticFiles.location("/public");
        GreetManager manager = new GreetManager();

        port(5000);

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