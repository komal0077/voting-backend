package com.example.VotingBackend.controllers;




import com.example.VotingBackend.models.Candidate;
import com.example.VotingBackend.models.ElectionHistory;
import com.example.VotingBackend.repositories.CandidateRepo;
import com.example.VotingBackend.repositories.ElectionHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.*;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "*")
public class ElectionHistoryController {

    @Autowired
    private CandidateRepo candidateRepository;

    @Autowired
    private ElectionHistoryRepo historyRepository;

    @PostMapping("/save")
    public String saveElectionHistory(@RequestParam String year, @RequestParam String state) {
        List<Candidate> candidates = candidateRepository.findAll();

        if (candidates.isEmpty()) {
            return "No candidates found for this election.";
        }

        Candidate winner = Collections.max(candidates, Comparator.comparingInt(Candidate::getVotesReceived));

        int totalVotes = candidates.stream().mapToInt(Candidate::getVotesReceived).sum();

        ElectionHistory history = new ElectionHistory(
                year,
                state,
                winner.getParty().getName(),
                winner.getName(),
                totalVotes
        );

        historyRepository.save(history);

        return "Election history saved for year " + year + " (" + state + ")";
    }

    @GetMapping
    public List<ElectionHistory> getAllHistory() {
        return historyRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHistory(@PathVariable Long id) {
        historyRepository.deleteById(id);
        return "Deleted Successfully";
    }
}