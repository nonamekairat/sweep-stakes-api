package com.example.SweepStakes.dto.user;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserDto {

    String username;
    String firstName;
    String lastName;
    int money;

}
