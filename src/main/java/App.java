import api.greetings.GreetingsApi;
import manager.AppStater;
import manager.GreetManager;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {


    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            staticFiles.location("/public");
            AppStater startApp = new AppStater();
            GreetManager manager = new GreetManager(startApp.getConnectionFromDb(), "english");
            GreetingsApi api = new GreetingsApi();
            port(startApp.getHerokuAssignedPort());

            get("/hello", (request, response) -> {
                Map<String, Object> map = new HashMap<>();
                map.put("users", manager.getUsers());
                map.put("counter", manager.getCount());
                return new ModelAndView(map, "hello.handlebars");
            }, new HandlebarsTemplateEngine());

            post("/hello", (request, response) -> {
                Map<String, Object> map = new HashMap<>();

                map.put("greeting", manager.getGreeting());
                map.put("users", manager.getUsers());
                map.put("counter", manager.getCount());
                return new ModelAndView(map, "hello.handlebars");
            }, new HandlebarsTemplateEngine());

            get("/greeted/:username", (request, response) -> {
                Map<String, Object> map = new HashMap<>();
                return new ModelAndView(map, "greeted.handlebars");
            }, new HandlebarsTemplateEngine());

            get("/api/greet/greeted/names", api.greeted_names());
            post("/api/greetings/greet", api.greet_user());
            get("/api/greetings/language", api.showLanguages());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
