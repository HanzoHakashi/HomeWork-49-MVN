package com.example.homework_49.utils;

import com.example.homework_49.models.Candidate;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class CandidateReader {
    public static List<Candidate> candidates() {
        String path = "templates/candidates.json";
        Gson gson = new Gson();
        try (InputStream inputStream = CandidateReader.class.getClassLoader().getResourceAsStream(path)) {
            InputStreamReader reader = new InputStreamReader(inputStream);
            Candidate[] candidatesArray = gson.fromJson(reader, Candidate[].class);
            List<Candidate> candidates = Arrays.asList(candidatesArray);
            return candidates;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}