package com.example.SweepStakes.controller;


import com.example.SweepStakes.dto.horse.HorseDTO;
import com.example.SweepStakes.dto.race.CreateRaceDTO;
import com.example.SweepStakes.dto.race.FinishRaceDTO;
import com.example.SweepStakes.dto.race.GetRaceDTO;
import com.example.SweepStakes.dto.race.UpdateRaceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SweepStakes.service.RaceService;

import java.util.List;

@RestController
@RequestMapping("/api/race")
@RequiredArgsConstructor
public class RaceController {

    private final RaceService raceService;

    @PostMapping("/create")
    public ResponseEntity<String> createNewRace(
            @RequestBody CreateRaceDTO createRaceDTO
    ) {
        return ResponseEntity.ok(raceService.save(createRaceDTO));
    }


    @PostMapping("/{id}/update")
    public ResponseEntity<String> updateRaceStatus(
            @RequestBody UpdateRaceDTO updateRaceDTO, @PathVariable Long id
    ) {
        return ResponseEntity.ok(raceService.updateStatus(updateRaceDTO, id));
    }

    @PostMapping("/{id}/finish")
    public ResponseEntity<String> finishRace(
            @RequestBody FinishRaceDTO finishRaceDTO, @PathVariable Long id
    ) {
        return ResponseEntity.ok(raceService.finishRace(finishRaceDTO, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRaceDTO> getRaceById(@PathVariable Long id) {
        return ResponseEntity.ok(raceService.getById(id));
    }


    @GetMapping("")
    public ResponseEntity<List<GetRaceDTO>> getRaces() {
        return ResponseEntity.ok(raceService.getAll());
    }

}
