package com.example.SweepStakes.repository;

import com.example.SweepStakes.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HorseRepository extends JpaRepository<Horse, Long> {
    Optional<Horse> findByName(String name);
}
