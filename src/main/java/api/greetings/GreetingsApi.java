package api.greetings;

import com.google.gson.Gson;
import jsonserialization.Deserializer;
import manager.greetings.GreetingsDbMethods;
import manager.languages.Language;
import manager.AppStater;
import manager.GreetManager;
import manager.languages.LanguageJson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.SQLException;

public class GreetingsApi {
    GreetManager manager;

    public GreetingsApi(GreetManager manager) {
        this.manager = manager;
    }

//    GreetManager appStarter = new AppStater()
////    AppStater appStater = new AppStater();
////    GreetManager manager = new GreetManager(appStater.getConnectionFromDb(), "english");
//
//    public GreetingsApi() throws Exception {
//    }
//
//    public Route greet_user() {
//        return this::handle;
//    }
//
//    public Route greeted_names() {
//        return this::handle2;
//    }
//
//    private Object handle(Request request, Response response) {
//        try {
//            response.type("application/json");
//            Deserializer deserializer = new Gson().fromJson(request.body(), Deserializer.class);
//            manager.greet.greet(deserializer.getUserName(), Language.valueOf(deserializer.getLanguage()));
//            System.out.println(manager.getGreeting());
//            return manager.getGreeting();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        response.body();
//        return manager.getGreeting();
//    }
//
//    private Object handle2(Request request, Response response) throws SQLException {
//        return manager.getNames();
//    }
//
//    public Route showLanguages() {
//        return (request, response) -> manager.getLanguageList();
//    }
//
//    public Route getGreetingMessage() {
//        return (request, response) -> manager.getGreeting();
//    }

    public Route greet_user() {
        return (request, response) -> {
            response.type("application/json");
            Deserializer deserializer = new Gson().fromJson(request.body(), Deserializer.class);
            String greeting = manager.greet(deserializer.getUserName(), Language.valueOf(deserializer.getLanguage()));
            System.out.println(greeting);
            return new LanguageJson(greeting);
        };
    }
}
