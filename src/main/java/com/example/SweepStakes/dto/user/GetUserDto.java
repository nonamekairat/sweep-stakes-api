package com.example.SweepStakes.dto.user;


import com.example.SweepStakes.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetUserDto {


    String username;
    String firstName;
    String lastName;
    int money;

    public GetUserDto getUserDto(User user){
        return GetUserDto.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .money(user.getMoney())
                .build();
    }

}
