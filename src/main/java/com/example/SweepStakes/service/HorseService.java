package com.example.SweepStakes.service;


import com.example.SweepStakes.dto.horse.HorseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.SweepStakes.repository.*;
import com.example.SweepStakes.model.Horse;

@Service
@RequiredArgsConstructor
public class HorseService {

    private final HorseRepository horseRepository;
    private final ModelMapper mapper;

    public String save(HorseDTO request) {
        Horse horse = mapper.map(request, Horse.class);
        horseRepository.save(horse);
        return "horse is successfully created";
    }
}
