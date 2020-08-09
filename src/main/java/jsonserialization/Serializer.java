package jsonserialization;

import com.google.gson.Gson;
import greetings.People;

public class Serializer {
    Gson gson = new Gson();

    public String fromObjectToJson(Object objectData) {
        return gson.toJson(objectData);
    }
}
