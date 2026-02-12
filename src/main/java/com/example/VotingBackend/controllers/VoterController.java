package com.example.VotingBackend.controllers;





import com.example.VotingBackend.models.Voter;
import com.example.VotingBackend.services.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin(origins = "*")
public class VoterController {

    @Autowired
    private VoterService voterService;

    @GetMapping
    public List<Voter> getAllVoters() {
        return voterService.getAllVoters();
    }

    @GetMapping("/{id}")
    public Optional<Voter> getVoterById(@PathVariable Long id) {
        return voterService.getVoterById(id);
    }

    @PostMapping
    public Voter addVoter(@RequestBody Voter voter) {
        return voterService.addVoter(voter);
    }

    @PutMapping("/{id}")
    public Voter updateVoter(@PathVariable Long id, @RequestBody Voter voter) {
        return voterService.updateVoter(id, voter);
    }

    @DeleteMapping("/{id}")
    public String deleteVoter(@PathVariable Long id) {
        voterService.deleteVoter(id);
        return "Voter with ID " + id + " deleted successfully.";
    }
}

