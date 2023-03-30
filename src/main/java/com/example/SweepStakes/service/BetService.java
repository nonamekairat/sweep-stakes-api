package com.example.SweepStakes.service;


import com.example.SweepStakes.dto.bet.BetDTO;
import com.example.SweepStakes.dto.bet.GetBetDTO;
import com.example.SweepStakes.exception.NotEnoughMoneyException;
import com.example.SweepStakes.exception.ResourceNotFoundException;
import com.example.SweepStakes.exception.WrongHorseInRaceException;
import com.example.SweepStakes.model.Bet;
import com.example.SweepStakes.model.Horse;
import com.example.SweepStakes.model.Race;
import com.example.SweepStakes.model.User;
import com.example.SweepStakes.repository.BetRepository;
import com.example.SweepStakes.repository.HorseRepository;
import com.example.SweepStakes.repository.RaceRepository;
import com.example.SweepStakes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BetService {


    private final RaceRepository raceRepository;
    private final HorseRepository horseRepository;
    private final BetRepository betRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public String makeBet(BetDTO betDTO, User user) {

        if(user.getMoney() < betDTO.getSum()) {
            throw new NotEnoughMoneyException("not enough money"); // todo: переименовать NotEnoughMoney во что то другое
        }

        Race race = raceRepository.findById(betDTO.getRaceId()).orElseThrow(
                () -> new ResourceNotFoundException("race not found")
        );
        Horse horse = horseRepository.findById(betDTO.getHorseId()).orElseThrow(
                () -> new ResourceNotFoundException("horse not found")
        );
        boolean isHorseInRace = false;

        for (int i = 0; i < race.getHorses().size(); i++) {
            isHorseInRace |= Objects.equals(horse.getId(), race.getHorses().get(i).getId());
        }

        if(!isHorseInRace){
            throw new WrongHorseInRaceException("horse not found");
        }

        user.setMoney(user.getMoney() - betDTO.getSum());
        userRepository.save(user);

        Bet bet = Bet.builder()
                .sum(betDTO.getSum())
                .user(user)
                .race(race)
                .horse(horse)
                .build();




        betRepository.save(bet);



        return "bet is successfully registered";
    }

    public List<GetBetDTO> getAllByUser(User user) {

        List<GetBetDTO> list = betRepository.findAllByUser(user).stream()
                .map(bet -> mapper.map(bet, GetBetDTO.class)).toList();


        return list;
    }
}
