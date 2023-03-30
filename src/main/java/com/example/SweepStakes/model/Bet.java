package com.example.SweepStakes.model;


import com.example.SweepStakes.model.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "bet")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bet extends BaseEntity {

    @Column(name = "sum")
    int sum;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;


    @ManyToOne
    @JoinColumn(name = "race_id")
    Race race;

    @ManyToOne
    @JoinColumn(name = "horse_id")
    Horse horse;


}
