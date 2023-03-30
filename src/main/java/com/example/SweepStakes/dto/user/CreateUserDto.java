package com.example.SweepStakes.dto.user;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserDto {

    String username;

    String password;

    String firstName;

    String lastName;

}
