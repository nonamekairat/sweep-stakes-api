package com.example.SweepStakes.exception.custom;

public class WrongHorseInRaceException extends RuntimeException{

    public WrongHorseInRaceException(String message) {
        super(message);
    }
}
