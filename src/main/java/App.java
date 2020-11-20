import manager.jsonserialization.*;
import manager.api.greetings.GreetingsApi;
import manager.AppStater;
import manager.GreetManager;
import manager.greetings.GreetingsDbMethods;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAmount;

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
//        try {
//            Class.forName("org.h2.Driver");
//            staticFiles.location("/public");
//            GreetManager manager = new GreetManager();
//            GreetingsDbMethods methods = new GreetingsDbMethods(getConnectionFromDb());
//            GreetingsApi api = new GreetingsApi(manager, methods);
//            AppStater appStater = new AppStater(getConnectionFromDb());
//            port(getHerokuAssignedPort());
//
//            get("/api/greeted/names", api.greeted_names());
//            post("/api/greetings/greet", api.greet_user(), new Serializer());
//            get("/api/greetings/language", api.showLanguages(), new Serializer());
//            get("/api/greetings/counter", api.get_counter());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        LocalDate localDate1 = LocalDate.parse("2001-01-21");
        LocalDate localDate2 = LocalDate.of(2002, Month.JANUARY, 23);
        localDate2.plusDays(2);

        System.out.println();
        System.out.println(localDate2.atTime(23, 35));
        System.out.println();
    }
}