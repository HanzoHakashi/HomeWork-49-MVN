package com.example.homework_49.models;


import com.example.homework_49.utils.CandidateReader;

import java.util.ArrayList;
import java.util.List;

public class CandidateDataModel {
    public List<Candidate> candidates = new ArrayList<>();
    private CandidateReader candidateReader = new CandidateReader();

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public CandidateDataModel() {
        this.candidates = candidateReader.candidates();
    }


}
