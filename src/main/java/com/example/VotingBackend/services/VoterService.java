package com.example.VotingBackend.services;


import com.example.VotingBackend.models.Voter;
import com.example.VotingBackend.repositories.VoterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoterService {

    @Autowired
    private VoterRepo voterRepository;

    public List<Voter> getAllVoters() {
        return voterRepository.findAll();
    }

    public Optional<Voter> getVoterById(Long id) {
        return voterRepository.findById(id);
    }

    public Voter addVoter(Voter voter) {
        // Check if email already exists
        Voter existing = voterRepository.findByEmail(voter.getEmail());
        if (existing != null) {
            throw new RuntimeException("Email already registered!");
        }
        return voterRepository.save(voter);
    }

    public Voter updateVoter(Long id, Voter updatedVoter) {
        return voterRepository.findById(id).map(voter -> {
            voter.setName(updatedVoter.getName());
            voter.setEmail(updatedVoter.getEmail());
            voter.setPassword(updatedVoter.getPassword());
            voter.setAge(updatedVoter.getAge());
            voter.setGender(updatedVoter.getGender());
            voter.setState(updatedVoter.getState());
            voter.setConstituency(updatedVoter.getConstituency());
            voter.setHasVoted(updatedVoter.getHasVoted());
            voter.setVotedCandidateId(updatedVoter.getVotedCandidateId());
            return voterRepository.save(voter);
        }).orElse(null);
    }

    public void deleteVoter(Long id) {
        voterRepository.deleteById(id);
    }
}