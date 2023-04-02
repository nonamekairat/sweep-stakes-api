package com.example.SweepStakes.controller;

import com.example.SweepStakes.dto.user.GetUserDto;
import com.example.SweepStakes.dto.user.MoneyDto;
import com.example.SweepStakes.model.User;
import com.example.SweepStakes.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")

public class UserController {

    private final UserService userService;

    @GetMapping("/get-user")
    public ResponseEntity<GetUserDto> getUserDtoResponseEntity(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(userService.getUserDto(user));
    }

    @PostMapping("/add-money")
    public ResponseEntity<String> addMoney(@AuthenticationPrincipal User user,
                                               @RequestBody MoneyDto money){
        return ResponseEntity.ok(userService.addMoney(user, money));
    }

    @GetMapping("/Hello")
    public ResponseEntity<String> getUserDtoResponseEntity(){
        return ResponseEntity.ok("Okay");
    }

}
