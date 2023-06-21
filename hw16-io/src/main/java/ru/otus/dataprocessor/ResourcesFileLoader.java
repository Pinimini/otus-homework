package ru.otus.dataprocessor;

import com.google.gson.Gson;
import jakarta.json.Json;
import jakarta.json.JsonReader;
import ru.otus.model.Measurement;

import java.util.List;

public class ResourcesFileLoader implements Loader {
    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {

        List<Measurement> measurements;
        try (JsonReader reader = Json.createReader(
                ResourcesFileLoader.class.getClassLoader().getResourceAsStream(fileName))) {
            Gson gson = new Gson();
            measurements = List.of(gson.fromJson(reader.read().toString(), Measurement[].class));
        }
        return measurements;
    }
}
