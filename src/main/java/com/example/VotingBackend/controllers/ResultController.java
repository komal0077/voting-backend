package com.example.VotingBackend.controllers;


import com.example.VotingBackend.models.Candidate;
import com.example.VotingBackend.models.Party;
import com.example.VotingBackend.repositories.CandidateRepo;
import com.example.VotingBackend.repositories.PartyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins = "*")
public class ResultController {

    @Autowired
    private CandidateRepo candidateRepository;

    @Autowired
    private PartyRepo partyRepository;

    /**
     * Get winner candidate
     */
    @GetMapping("/winner")
    public Map<String, Object> getWinner() {
        List<Candidate> allCandidates = candidateRepository.findAll();

        if (allCandidates.isEmpty()) {
            return Map.of("message", "No candidates found");
        }



        Candidate winner = Collections.max(allCandidates, Comparator.comparingInt(Candidate::getVotesReceived));

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("winnerCandidate", winner.getName());
        response.put("party", winner.getParty() != null ? winner.getParty().getName() : "Independent");
        response.put("votesReceived", winner.getVotesReceived());
        response.put("state", winner.getState());                   // <-- added
        response.put("electionYear", winner.getElectionYear());    // <-- added
        response.put("symbol", winner.getSymbol());                // <-- added
        return response;
    }

    /**
     * Get vote count per candidate
     */
    @GetMapping("/candidates")
    public List<Map<String, Object>> getCandidateVotes() {
        List<Candidate> allCandidates = candidateRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Candidate c : allCandidates) {
            Map<String, Object> map = new HashMap<>();
            map.put("candidateName", c.getName());
            map.put("party", c.getParty().getName());
            map.put("votesReceived", c.getVotesReceived());
            result.add(map);
        }
        return result;
    }

    /**
     * Get party-wise vote count
     */
    @GetMapping("/parties")
    public List<Map<String, Object>> getPartyResults() {
        List<Party> allParties = partyRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Party p : allParties) {
            int totalVotes = p.getCandidates().stream().mapToInt(Candidate::getVotesReceived).sum();
            Map<String, Object> map = new HashMap<>();
            map.put("id",p.getId());
            map.put("partyName", p.getName());
            map.put("totalVotes", totalVotes);
            result.add(map);
        }

        return result;
    }
}
