package com.example.SweepStakes.controller;



import com.example.SweepStakes.dto.AuthenticationResponse;
import com.example.SweepStakes.dto.horse.HorseDTO;
import com.example.SweepStakes.service.HorseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/horse")
@RequiredArgsConstructor
public class HorseController {

    private final HorseService horseService;

    @PostMapping("/create")
    public ResponseEntity<String> createNewHorse(
            @RequestBody HorseDTO request
    ) {
        return ResponseEntity.ok(horseService.save(request));
    }

}
