package com.example.VotingBackend.services;


import com.example.VotingBackend.models.Party;
import com.example.VotingBackend.repositories.PartyRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyService {

    private final PartyRepo partyRepo;

    public PartyService(PartyRepo partyRepo) {
        this.partyRepo = partyRepo;
    }

    public List<Party> getAllParties() {
        return partyRepo.findAll();
    }

    public Party addParty(Party party) {
        return partyRepo.save(party);
    }

    public void deleteParty(Long id) {
        // Load the Party entity
        Party party = partyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Party not found with id: " + id));

        // Cascade delete will remove all candidates linked to this party
        partyRepo.delete(party);
    }
}