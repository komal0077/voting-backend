package com.example.VotingBackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.persistence.*;

@Entity
public class ElectionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String year;
    private String state;
    private String winningParty;
    private String winnerCandidate;
    private int totalVotes;

    public ElectionHistory() {}

    public ElectionHistory(String year, String state, String winningParty, String winnerCandidate, int totalVotes) {
        this.year = year;
        this.state = state;
        this.winningParty = winningParty;
        this.winnerCandidate = winnerCandidate;
        this.totalVotes = totalVotes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWinningParty() {
        return winningParty;
    }

    public void setWinningParty(String winningParty) {
        this.winningParty = winningParty;
    }

    public String getWinnerCandidate() {
        return winnerCandidate;
    }

    public void setWinnerCandidate(String winnerCandidate) {
        this.winnerCandidate = winnerCandidate;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }
// getters + setters...
}



