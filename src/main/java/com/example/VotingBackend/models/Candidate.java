package com.example.VotingBackend.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;
    private String gender;
    private String state;
    private String constituency;
    private Integer electionYear;
    private Integer votesReceived = 0;
    private String resultStatus; // WON, LOST, PENDING
    private String education;

    private String symbol;

//
//    @ManyToOne
//    @JoinColumn(name = "party_id")

    @ManyToOne
    @JoinColumn(name = "party_id", nullable = false)

    @JsonIgnoreProperties("candidates")
    private Party party;

    public Candidate() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getConstituency() { return constituency; }
    public void setConstituency(String constituency) { this.constituency = constituency; }

    public Integer getElectionYear() { return electionYear; }
    public void setElectionYear(Integer electionYear) { this.electionYear = electionYear; }

    public Integer getVotesReceived() { return votesReceived; }
    public void setVotesReceived(Integer votesReceived) { this.votesReceived = votesReceived; }

    public String getResultStatus() { return resultStatus; }
    public void setResultStatus(String resultStatus) { this.resultStatus = resultStatus; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }


    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }



    public Party getParty() { return party; }
    public void setParty(Party party) { this.party = party; }
}

















