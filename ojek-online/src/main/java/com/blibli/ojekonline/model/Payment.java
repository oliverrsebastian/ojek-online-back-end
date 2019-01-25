package com.blibli.ojekonline.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String name;
}
