package com.example.SweepStakes.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum RaceStatus {

    ANNOUNCED("анонсированный"), ACTIVE("активный"), FINISHED("законченный");
    private final String status;


    public static RaceStatus of(String raceStatus) {
        return Stream.of(RaceStatus.values())
                .filter(p -> p.getStatus().equals(raceStatus))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
