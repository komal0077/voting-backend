package com.example.VotingBackend.repositories;



import com.example.VotingBackend.models.User;
import com.example.VotingBackend.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}