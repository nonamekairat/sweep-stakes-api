package com.example.SweepStakes.repository;

import com.example.SweepStakes.dto.bet.GetBetDTO;
import com.example.SweepStakes.model.Bet;
import com.example.SweepStakes.model.Race;
import com.example.SweepStakes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findAllByRace(Race race);

    List<Bet> findAllByUser(User user);
}
