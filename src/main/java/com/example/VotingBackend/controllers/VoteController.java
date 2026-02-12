package com.example.VotingBackend.controllers;





import com.example.VotingBackend.dtos.VoteRequest;
import com.example.VotingBackend.models.Candidate;
import com.example.VotingBackend.models.Voter;
import com.example.VotingBackend.repositories.CandidateRepo;
import com.example.VotingBackend.repositories.VoterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.Optional;

@RestController
@RequestMapping("/api/vote")
@CrossOrigin(origins = "*")
public class VoteController {

    @Autowired
    private VoterRepo voterRepository;

    @Autowired
    private CandidateRepo candidateRepository;

    /**
     * Cast a vote
     * Example:
     * POST /api/vote?voterId=1&candidateId=2
     */
    @PostMapping
    public String castVote(@RequestBody VoteRequest voteRequest) {

        Long voterId = voteRequest.getVoterId();
        Long candidateId = voteRequest.getCandidateId();

        Optional<Voter> voterOpt = voterRepository.findById(voterId);
        Optional<Candidate> candidateOpt = candidateRepository.findById(candidateId);

        if (voterOpt.isEmpty() || candidateOpt.isEmpty()) {
            return "❌ Voter or Candidate not found!";
        }

        Voter voter = voterOpt.get();
        Candidate candidate = candidateOpt.get();

        if (voter.getHasVoted() != null && voter.getHasVoted()) {
            return "⚠️ You have already voted!";
        }

        // Increment votes
        candidate.setVotesReceived(candidate.getVotesReceived() + 1);
        candidateRepository.save(candidate);

        // Mark voter as voted
        voter.setHasVoted(true);
        voter.setVotedCandidateId(candidate.getId());
        voterRepository.save(voter);

        String partyName = (candidate.getParty() != null)
                ? candidate.getParty().getName()
                : "Independent";

        return "✅ Vote cast successfully for candidate: " + candidate.getName() +
                " (" + partyName + ")";
    }
}
