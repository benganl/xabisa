package za.co.wyzetech.xabisa.security.utils;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class JsonUtils {

    private final Gson gson;

    protected JsonUtils() {
        gson = new GsonBuilder().setLenient().serializeNulls().create();
    }

    public String toJson(Object object) {
        return gson.toJson(object);
    }

    public <T> T fromJson(String json, Class<T> klass) {
        return gson.fromJson(json, klass);
    }
}
