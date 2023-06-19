package ru.otus.dataprocessor;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {
    private final String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        try (var bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            String resultJson = new Gson().toJson(data);
            bufferedWriter.write(resultJson);
        } catch (IOException e) {
            System.out.println("Произошла ошибка, при записи json в файл");
            throw new RuntimeException(e);
        }
    }
}
