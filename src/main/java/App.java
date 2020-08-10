import api.greetings.GreetingsApi;
import manager.AppStater;
import manager.GreetManager;
import manager.greetings.GreetingsDbMethods;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static public int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    static public Connection getConnectionFromDb() throws Exception {
        String dbDiskURL = "jdbc:h2:file:./greetings_db";
        Jdbi jdbi = Jdbi.create(dbDiskURL, "sa", "");
        Handle handle = jdbi.open();
        handle.execute("create table if not exists greetings ( id integer identity, name text not null, count_time int )");
        return DriverManager.getConnection(dbDiskURL, "sa", "");
    }

    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            staticFiles.location("/public");
            GreetManager manager = new AppStater(getConnectionFromDb());
//            GreetingsDbMethods methods = new GreetingsDbMethods(getConnectionFromDb());
            GreetingsApi api = new GreetingsApi(manager);
            port(getHerokuAssignedPort());


//            get("/hello", (request, response) -> {
//                Map<String, Object> map = new HashMap<>();
//                map.put("users", methods.getUsers());
//                map.put("counter", manager.getCount());
//                return new ModelAndView(map, "hello.handlebars");
//            }, new HandlebarsTemplateEngine());
//
//            post("/hello", (request, response) -> {
//                Map<String, Object> map = new HashMap<>();
//
//                map.put("greeting", manager.getGreeting());
//                map.put("users", manager.getUsers());
//                map.put("counter", manager.getCount());
//                return new ModelAndView(map, "hello.handlebars");
//            }, new HandlebarsTemplateEngine());
//
//            get("/greeted/:username", (request, response) -> {
//                Map<String, Object> map = new HashMap<>();
//                return new ModelAndView(map, "greeted.handlebars");
//            }, new HandlebarsTemplateEngine());

//            get("/api/greet/greeted/names", api.greeted_names());
            post("/api/greetings/greet", api.greet_user());
//            get("/api/greetings/language", api.showLanguages());
//            get("/api/greetings/message", api.getGreetingMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
