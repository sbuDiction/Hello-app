package api.greetings;

import com.google.gson.Gson;
import jsonserialization.Deserializer;
import languages.Language;
import manager.AppStater;
import manager.GreetManager;
import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.SQLException;
import java.util.List;

public class GreetingsApi {
    GreetManager connection;
    AppStater appStater = new AppStater();
    GreetManager manager = new GreetManager(appStater.getConnectionFromDb(), "english");

    public GreetingsApi() throws Exception {
    }

    public Route greet_user() {
        return this::handle;
    }

    public Route greeted_names() {
        return this::handle2;
    }

    private Object handle(Request request, Response response) {
        try {
            response.type("application/json");
            Deserializer deserializer = new Gson().fromJson(request.body(), Deserializer.class);
            manager.greet.greet(deserializer.getUserName(), Language.valueOf(deserializer.getLanguage()));
            manager.greetDbManagement(manager.getUsername());
            System.out.println(manager.getGreeting());
            return "hello";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello";
    }

    private Object handle2(Request request, Response response) throws SQLException {
        return manager.getNames();
    }

    public Route showLanguages() {
        return (request, response) -> manager.getLanguageList();
    }
}
