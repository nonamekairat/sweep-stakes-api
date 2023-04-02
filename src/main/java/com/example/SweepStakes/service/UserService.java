package com.example.SweepStakes.service;

import com.example.SweepStakes.dto.AuthenticationResponse;
import com.example.SweepStakes.dto.user.AuthUserDto;
import com.example.SweepStakes.dto.user.CreateUserDto;
import com.example.SweepStakes.dto.user.GetUserDto;
import com.example.SweepStakes.dto.user.MoneyDto;
import com.example.SweepStakes.exception.custom.UserAlreadyExistException;
import com.example.SweepStakes.model.User;
import com.example.SweepStakes.model.enums.Role;
import com.example.SweepStakes.model.enums.Status;
import com.example.SweepStakes.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;




    public AuthenticationResponse register(CreateUserDto request) throws UserAlreadyExistException {
        if(userRepository.existsByUsername(request.getUsername()))
            throw new UserAlreadyExistException(
                    "username",
                    "User with this username is already exists"
            );
        var user = User.builder()
                .status(Status.ACTIVE)
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_ADMIN)
                .money(0)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthUserDto request) {

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            )
        );
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public GetUserDto getUserDto(User user) {
        return new GetUserDto().getUserDto(user);
    }

    public String addMoney(User user, MoneyDto money) {

        user.setMoney(user.getMoney() + money.getMoney());
        userRepository.save(user);

        return "money successfully added";
    }
}
