package com.blibli.ojekonline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private int balance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    @JsonIgnore
    private List<Booking> bookingList;
}
