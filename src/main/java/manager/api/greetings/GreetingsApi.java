package manager.api.greetings;

import com.google.gson.Gson;
import manager.jsonserialization.Deserializer;
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

    public Route greeted_names() {
        return this::handle2;
    }

    private Object handle2(Request request, Response response) throws SQLException {
        String offset = request.queryParams("offset");
        request.body();
        System.out.println(request.body());
        System.out.println(offset);
        return greetingsDbMethods.getNames(offset != null ? Integer.parseInt(offset) : 0);
    }

    public Route showLanguages() {
        return (request, response) -> manager.getLanguageList();
    }

    public Route greet_user() {
        return this::handleGreet;
    }

    private Object handleGreet(Request request, Response response) {
        response.type("application/json");
        Deserializer deserializer = new Gson().fromJson(request.body(), Deserializer.class);
        greetingsDbMethods.checkNameDuplicate(manager.addName(deserializer.getUserName()));
        return manager.greet(deserializer.getUserName(), Language.valueOf(deserializer.getLanguage()));
    }

    public Route get_counter() {
        return (request, response) -> greetingsDbMethods.getCount();
    }
}
