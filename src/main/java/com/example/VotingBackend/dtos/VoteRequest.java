package com.example.VotingBackend.dtos;



public class VoteRequest {

    private Long voterId;
    private Long candidateId;

    public VoteRequest(){

    }
    // Getters & Setters
    public Long getVoterId() { return voterId; }
    public void setVoterId(Long voterId) { this.voterId = voterId; }

    public Long getCandidateId() { return candidateId; }
    public void setCandidateId(Long candidateId) { this.candidateId = candidateId; }
}

