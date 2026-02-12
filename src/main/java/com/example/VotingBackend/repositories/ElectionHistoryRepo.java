package com.example.VotingBackend.repositories;



import com.example.VotingBackend.models.ElectionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionHistoryRepo extends JpaRepository<ElectionHistory, Long> {
}
