package com.example.SweepStakes.dto.race;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetRaceDTO {

    Long id;
    String name;
    List<String> horses;
    String raceStatus;


}
