package manager.api.greetings;

import com.google.gson.Gson;
import jsonserialization.Deserializer;
import manager.greetings.GreetingsDbMethods;
import manager.languages.Language;
import manager.GreetManager;
import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.SQLException;

public class GreetingsApi {
    GreetManager manager;
    GreetingsDbMethods greetingsDbMethods;

    public GreetingsApi(GreetManager manager, GreetingsDbMethods greetingsDbMethods) {
        this.manager = manager;
        this.greetingsDbMethods = greetingsDbMethods;
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
    public Route greeted_names() {
        return this::handle2;
    }

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
    private Object handle2(Request request, Response response) throws SQLException {
        return greetingsDbMethods.getNames();
    }

    //
    public Route showLanguages() {
        return (request, response) -> manager.getLanguageList();
    }
//
//    public Route getGreetingMessage() {
//        return (request, response) -> manager.getGreeting();
//    }

    public Route greet_user() {
        return this::handle;
    }

    private Object handle(Request request, Response response) throws SQLException {
        response.type("application/json");
        Deserializer deserializer = new Gson().fromJson(request.body(), Deserializer.class);
//        manager.greet(deserializer.getUserName(), Language.valueOf(deserializer.getLanguage()));
        System.out.println(manager.greet(deserializer.getUserName(), Language.valueOf(deserializer.getLanguage())) + " this one");
        greetingsDbMethods.checkNameDuplicate(manager.addName(deserializer.getUserName()));
        return manager.greet(deserializer.getUserName(), Language.valueOf(deserializer.getLanguage()));
    }
}
