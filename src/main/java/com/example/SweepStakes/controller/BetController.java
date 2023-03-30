package com.example.SweepStakes.controller;


import com.example.SweepStakes.dto.bet.BetDTO;
import com.example.SweepStakes.dto.bet.GetBetDTO;
import com.example.SweepStakes.model.User;
import com.example.SweepStakes.service.BetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bet")
@RequiredArgsConstructor
public class BetController {

    private final BetService betService;

    @PostMapping("/make")
    public ResponseEntity<?> makeBet(@RequestBody BetDTO betDTO, @AuthenticationPrincipal User user){
        return ResponseEntity.ok(betService.makeBet(betDTO, user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetBetDTO>> getInfoAboutBet(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(betService.getAllByUser(user));
    }



}
