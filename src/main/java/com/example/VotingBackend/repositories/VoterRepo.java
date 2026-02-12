package com.example.VotingBackend.repositories;


import com.example.VotingBackend.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepo extends JpaRepository<Voter, Long> {
    Voter findByEmail(String email);
}