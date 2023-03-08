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


}
