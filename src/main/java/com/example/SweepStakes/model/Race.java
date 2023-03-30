package com.example.SweepStakes.model;


import com.example.SweepStakes.model.baseEntity.BaseEntity;
import com.example.SweepStakes.model.enums.RaceStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "race")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Race extends BaseEntity {

    @Column(name = "race_name")
    String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "horse_race_item",
            joinColumns = @JoinColumn(name="horse_id"),
            inverseJoinColumns = @JoinColumn(name = "race_id"))
    List<Horse> horses;

    @Enumerated(EnumType.STRING)
    RaceStatus raceStatus;



}
