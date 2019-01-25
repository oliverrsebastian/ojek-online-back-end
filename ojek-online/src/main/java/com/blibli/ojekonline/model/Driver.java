package com.blibli.ojekonline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private int balance;

    @Column
    private String plateNumber;

    @Column
    private String joinedDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driver")
    @JsonIgnore
    private List<Booking> bookingList;
}
