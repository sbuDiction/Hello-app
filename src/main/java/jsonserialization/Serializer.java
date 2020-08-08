package jsonserialization;

import com.google.gson.Gson;
import greetings.People;

public class Serializer {
    Gson gson = new Gson();
    private String json;
    private People objectData;

    public String fromObjectToJson(People objectData) {
        json = gson.toJson(objectData);
        System.out.println(json + " this");
        return json;
    }

    public String getJson() {
//        System.out.println(json + " second one");
        return json;
    }
}
