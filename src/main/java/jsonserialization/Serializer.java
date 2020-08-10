package jsonserialization;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class Serializer implements ResponseTransformer {
    private Gson gson = new Gson();

    public String fromObjectToJson(Object objectData) {
        return gson.toJson(objectData);
    }

    @Override
    public String render(Object objectData) throws Exception {
        return gson.toJson(objectData);
    }
}
