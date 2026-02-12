package com.example.VotingBackend.services;


import com.example.VotingBackend.models.Candidate;
import com.example.VotingBackend.repositories.CandidateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepo candidateRepository;

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    public Candidate addCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidate(Long id, Candidate updatedCandidate) {
        return candidateRepository.findById(id).map(candidate -> {
            candidate.setName(updatedCandidate.getName());
            candidate.setAge(updatedCandidate.getAge());
            candidate.setGender(updatedCandidate.getGender());
            candidate.setState(updatedCandidate.getState());
            candidate.setConstituency(updatedCandidate.getConstituency());
            candidate.setElectionYear(updatedCandidate.getElectionYear());
            candidate.setVotesReceived(updatedCandidate.getVotesReceived());
            candidate.setResultStatus(updatedCandidate.getResultStatus());
            candidate.setEducation(updatedCandidate.getEducation());

            candidate.setSymbol(updatedCandidate.getSymbol());

            candidate.setParty(updatedCandidate.getParty());
            return candidateRepository.save(candidate);
        }).orElse(null);
    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    public List<Candidate> getCandidatesByState(String state) {
        return candidateRepository.findByState(state);
    }

    public List<Candidate> getCandidatesByYear(Integer year) {
        return candidateRepository.findByElectionYear(year);
    }

    public List<Candidate> getCandidatesByParty(String partyName) {
        return candidateRepository.findByParty_Name(partyName);
    }
}
