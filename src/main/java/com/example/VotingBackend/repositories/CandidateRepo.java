package com.example.VotingBackend.repositories;



import com.example.VotingBackend.models.Candidate;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {
    List<Candidate> findByState(String state);
    List<Candidate> findByElectionYear(Integer year);
    List<Candidate> findByParty_Name(String partyName);
    List<Candidate>  findByPartyId(Long PartyId);
}