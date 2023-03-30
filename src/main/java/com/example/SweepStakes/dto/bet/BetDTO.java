package com.example.SweepStakes.dto.bet;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BetDTO {

    int sum;
    Long raceId;
    Long horseId;
}
