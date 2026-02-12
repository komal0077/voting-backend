package com.example.VotingBackend.repositories;


import com.example.VotingBackend.models.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepo extends JpaRepository<Party,Long> {

}
