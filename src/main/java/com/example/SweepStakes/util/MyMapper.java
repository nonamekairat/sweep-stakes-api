package com.example.SweepStakes.util;


import com.example.SweepStakes.dto.bet.GetBetDTO;
import com.example.SweepStakes.dto.race.CreateRaceDTO;
import com.example.SweepStakes.dto.race.GetRaceDTO;
import com.example.SweepStakes.dto.race.UpdateRaceDTO;
import com.example.SweepStakes.model.Bet;
import com.example.SweepStakes.model.Horse;
import com.example.SweepStakes.model.Race;
import com.example.SweepStakes.model.enums.RaceStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MyMapper {



    public ModelMapper getMapper(){
        ModelMapper mapper = new ModelMapper();

        Converter<RaceStatus, String> raceStatusStringConverter =
                src -> src.getSource() == null ? null : src.getSource().getStatus();


        Converter<String, RaceStatus> stringRaceStatusConverter =
                src -> src.getSource() == null ? null : RaceStatus.of(src.getSource());

        Converter<Horse, String> horseStringConverter =
                src -> src.getSource() == null ? null : src.getSource().getName();
        Converter<Race, String> raceStringConverter =
                src -> src.getSource() == null ? null : src.getSource().getName();


        Converter<List<String>, List<Horse>> createRaceDTORaceConverter =
                src -> src.getSource() == null ? null : src.getSource()
                        .stream()
                        .map(name -> {
                            Horse horse = new Horse();
                            horse.setName(name);
                            return horse;
                        }).collect(Collectors.toList());

        Converter<List<Horse>, List<String>> listHorseListStringConverter =
                src -> src.getSource() == null ? null : src.getSource()
                        .stream()
                        .map(Horse::getName).collect(Collectors.toList());



        mapper.typeMap(CreateRaceDTO.class, Race.class)
                .addMappings(m -> m.using(createRaceDTORaceConverter)
                        .map(CreateRaceDTO::getHorses, Race::setHorses));

        mapper.typeMap(Bet.class, GetBetDTO.class)
                .addMappings(m -> m.using(horseStringConverter)
                        .map(Bet::getHorse, GetBetDTO::setHorse));

        mapper.typeMap(Bet.class, GetBetDTO.class)
                .addMappings(m -> m.using(raceStringConverter)
                        .map(Bet::getRace, GetBetDTO::setRace));

//        mapper.typeMap(Bet.class, GetBetDTO.class)
//                .addMappings(m -> m.using(betGetBetDTOConverter)
//                        .map(CreateRaceDTO::getHorses, Race::setHorses));

//       todo: make it work

//        mapper.typeMap(UpdateRaceDTO.class, Race.class)
//                .addMappings(m -> m.using(raceStatusStringConverter)
//                        .map(UpdateRaceDTO::getStatus, Race::setRaceStatus));

        mapper.typeMap(Race.class, GetRaceDTO.class)
                .addMappings(m -> m.using(raceStatusStringConverter)
                        .map(Race::getRaceStatus, GetRaceDTO::setRaceStatus));

//        mapper.typeMap(Race.class, GetRaceDTO.class)
//                .addMappings(m -> m.using(stringRaceStatusConverter)
//                        .map(Race::getRaceStatus, GetRaceDTO::setRaceStatus));

        mapper.typeMap(Race.class, GetRaceDTO.class)
                .addMappings(m -> m.using(listHorseListStringConverter)
                        .map(Race::getHorses, GetRaceDTO::setHorses));

        // todo implement mappers

        return mapper;

    }


}
