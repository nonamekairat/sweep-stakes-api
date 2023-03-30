package com.example.SweepStakes.model.baseEntity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    //TODO check identity, auto
//
//    @CreationTimestamp
//    private LocalDateTime created;
//    //    later
//    @UpdateTimestamp
//    private LocalDateTime updated;

}
