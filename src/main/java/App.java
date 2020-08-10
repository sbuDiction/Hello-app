import jsonserialization.Serializer;
import manager.api.greetings.GreetingsApi;
import manager.AppStater;
import manager.GreetManager;
import manager.greetings.GreetingsDbMethods;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.sql.Connection;
import java.sql.DriverManager;

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
            GreetManager manager = new GreetManager();
            GreetingsDbMethods methods = new GreetingsDbMethods(getConnectionFromDb());
            GreetingsApi api = new GreetingsApi(manager, methods);
            AppStater appStater = new AppStater(getConnectionFromDb());
            port(getHerokuAssignedPort());

            get("/api/greet/greeted/names", api.greeted_names());
            post("/api/greetings/greet", api.greet_user(), new Serializer());
            get("/api/greetings/language", api.showLanguages(), new Serializer());
//            get("/manager.api/greetings/message", manager.api.getGreetingMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
