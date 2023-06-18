package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.Json;
import jakarta.json.JsonReader;
import ru.otus.model.Measurement;

import java.io.IOException;
import java.util.List;

public class ResourcesFileLoader implements Loader {
    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() throws IOException {
        //читает файл, парсит и возвращает результат
        ObjectMapper objectMapper = new ObjectMapper();
        try (JsonReader reader = Json.createReader(
                ResourcesFileLoader.class.getClassLoader().getResourceAsStream("inputData.json"))) {
            //List measurement = objectMapper.readValue(reader.toString(), List<>.class);
        }
        return null;
    }
}
