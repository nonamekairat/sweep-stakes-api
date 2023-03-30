package com.example.SweepStakes.controller;

import com.example.SweepStakes.dto.user.GetUserDto;
import com.example.SweepStakes.model.User;
import com.example.SweepStakes.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")

public class UserController {

    private final UserService userService;

    @GetMapping("/getUser")
    public ResponseEntity<GetUserDto> getUserDtoResponseEntity(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(userService.getUserDto(user));
    }

    @GetMapping("/Hello")
    public ResponseEntity<String> getUserDtoResponseEntity(){
        return ResponseEntity.ok("Okay");
    }

}
