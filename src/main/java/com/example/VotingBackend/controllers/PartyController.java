package com.example.VotingBackend.controllers;


import com.example.VotingBackend.models.Party;
import com.example.VotingBackend.services.PartyService;
import org.springframework.web.bind.annotation.*;

 import java.util.List;

@RestController
@RequestMapping("/api/parties")
@CrossOrigin(origins = "*")
public class PartyController {
    private final PartyService partyService;
    public PartyController(PartyService partyService){
        this.partyService=partyService;
    }
    @GetMapping
    public List<Party> getAlParties(){
        return partyService.getAllParties();
    }
    @PostMapping
    public Party addParty(@RequestBody Party party){
        return
                partyService.addParty(party);
    }
    @DeleteMapping("/{id}")
    public void deleteParty(@PathVariable Long id){
        partyService.deleteParty(id);
    }
}
