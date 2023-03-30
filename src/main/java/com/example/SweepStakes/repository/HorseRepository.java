package com.example.SweepStakes.repository;

import com.example.SweepStakes.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorseRepository extends JpaRepository<Horse, Long> {
}
