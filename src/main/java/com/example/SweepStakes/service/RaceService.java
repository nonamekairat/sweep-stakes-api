package com.example.SweepStakes.service;


import com.example.SweepStakes.dto.race.CreateRaceDTO;
import com.example.SweepStakes.dto.race.FinishRaceDTO;
import com.example.SweepStakes.dto.race.GetRaceDTO;
import com.example.SweepStakes.dto.race.UpdateRaceDTO;
import com.example.SweepStakes.exception.custom.ResourceNotFoundException;
import com.example.SweepStakes.model.Bet;
import com.example.SweepStakes.model.Horse;
import com.example.SweepStakes.model.Race;
import com.example.SweepStakes.model.User;
import com.example.SweepStakes.model.enums.RaceStatus;
import com.example.SweepStakes.repository.BetRepository;
import com.example.SweepStakes.repository.HorseRepository;
import com.example.SweepStakes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.SweepStakes.repository.RaceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RaceService {


    private final ModelMapper mapper;
    private final RaceRepository raceRepository;
    private final BetRepository betRepository;
    private final HorseRepository horseRepository;
    private final UserRepository userRepository;


    public String save(CreateRaceDTO createRaceDTO) {

        Race race = mapper.map(createRaceDTO, Race.class);
        race.setRaceStatus(RaceStatus.ACTIVE);
        raceRepository.save(race);

        return "race successfully created";
    }

    public String updateStatus(UpdateRaceDTO updateRaceDTO, Long id) {

        // todo: split logic updateRace and finish it

        Race race = raceRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("race not found")
        );
        if(race.getRaceStatus().equals(RaceStatus.FINISHED)) return "race is finished and can't be changed";
        // todo: create mapper, how to do this funcying shit
        RaceStatus raceStatus = RaceStatus.of(updateRaceDTO.getStatus());




        race.setRaceStatus(raceStatus);

        raceRepository.save(race);

        return "new status is successfully set";
    }

    public String finishRace(FinishRaceDTO finishRaceDTO, Long id) {

        Race race = raceRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("race not found")
        );



        List<Bet> bets = betRepository.findAllByRace(race);
        List<Horse> winners = new ArrayList<>();
        for (int i = 0; i < 1; i++) { // todo: make 3
            winners.add(horseRepository.findById(finishRaceDTO.getHorseId().get(i)).orElseThrow(
                    () -> new ResourceNotFoundException("horse not found")
            ));
        }

        int sum = bets.stream().mapToInt(Bet::getSum).sum();


        // todo: make better algo to count 2-3 places winners
        for (int i = 0; i < winners.size(); i++) {
            for (int j = 0; j < bets.size(); j++) {
                if(bets.get(i).getHorse() == winners.get(i)){
                    // sum / количество денег поставленной на эту лощадь * сколько поставил человек
                    int sumToHorse = getSumToHorseInRace(winners.get(i), race);

                    User user = bets.get(i).getUser();
                    user.setMoney(sum / sumToHorse * bets.get(i).getSum());
                    userRepository.save(user);
                }
            }
        }



        return "race is successfully finished, all money is calculated";
    }

    private int getSumToHorseInRace(Horse horse, Race race) {
        List<Bet> bets = betRepository.findAllByRace(race);
        int sum = 0;
        for (Bet bet : bets) {
            if (Objects.equals(bet.getHorse().getId(), horse.getId())) {
                sum += bet.getSum();
            }
        }
        return sum;
    }

    public List<GetRaceDTO> getAll() {
        return raceRepository.findAll().stream()
                .map(race -> mapper.map(race, GetRaceDTO.class))
                .collect(Collectors.toList());
    }

    public GetRaceDTO getById(Long id) {
        return mapper.map(raceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no such race")), GetRaceDTO.class);
    }
}
