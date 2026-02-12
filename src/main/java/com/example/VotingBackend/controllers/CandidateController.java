package com.example.VotingBackend.controllers;




import com.example.VotingBackend.models.Candidate;
import com.example.VotingBackend.models.Party;
import com.example.VotingBackend.repositories.PartyRepo;
import com.example.VotingBackend.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

  import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin(origins = "*")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private PartyRepo partyRepo;

    @GetMapping
    public List<Candidate> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public Optional<Candidate> getCandidateById(@PathVariable Long id) {
        return candidateService.getCandidateById(id);
    }

    //    @PostMapping
//    public Candidate addCandidate(@RequestBody
//                                      Candidate candidate) {
//        return candidateService.addCandidate(candidate);
//    }
    @PostMapping
    public Candidate addCandidate(@RequestBody Map<String, Object> data) {

        Candidate candidate = new Candidate();

        candidate.setName((String) data.get("name"));
        candidate.setAge((Integer) data.get("age"));
        candidate.setGender((String) data.get("gender"));
        candidate.setState((String) data.get("state"));
        candidate.setConstituency((String) data.get("constituency"));
        candidate.setElectionYear((Integer) data.get("electionYear"));
        candidate.setEducation((String) data.get("education"));
        candidate.setSymbol((String) data.get("symbol"));
        candidate.setResultStatus((String) data.get("resultStatus"));

        Long partyId = Long.valueOf(data.get("partyId").toString());
        Party party = partyRepo.findById(partyId).orElse(null);
        candidate.setParty(party);

        return candidateService.addCandidate(candidate);
    }

    @PutMapping("/{id}")
    public Candidate updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
        return candidateService.updateCandidate(id, candidate);
    }

    @DeleteMapping("/{id}")
    public String deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return "Candidate with ID " + id + " deleted successfully.";
    }

    @GetMapping("/state/{state}")
    public List<Candidate> getCandidatesByState(@PathVariable String state) {
        return candidateService.getCandidatesByState(state);
    }

    @GetMapping("/year/{year}")
    public List<Candidate> getCandidatesByYear(@PathVariable Integer year) {
        return candidateService.getCandidatesByYear(year);
    }

    @GetMapping("/party/{partyName}")
    public List<Candidate> getCandidatesByParty(@PathVariable String partyName) {
        return candidateService.getCandidatesByParty(partyName);
    }
}

