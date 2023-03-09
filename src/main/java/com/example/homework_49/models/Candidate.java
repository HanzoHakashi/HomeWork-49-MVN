package com.example.homework_49.models;

import lombok.Data;

@Data
public class Candidate {
    private String name;
    private String photo;
    private int candidateID;
    private int totalVotes;
    private double percentageRatio;
    private int alreadyVoted;

    public Candidate(String name, String photo, int candidateID, int totalVotes, double percentageRatio, int alreadyVoted) {
        this.name = name;
        this.photo = photo;
        this.candidateID = candidateID;
        this.totalVotes = totalVotes;
        this.percentageRatio = percentageRatio;
        this.alreadyVoted = alreadyVoted;
    }
}
