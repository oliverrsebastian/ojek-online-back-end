package com.blibli.ojekonline.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private int paymentId;

    @Column
    private String bookedDate;

    @Column
    private String scheduledDate;

    @Column
    private int balanceTaken;

    @Column
    private boolean canceled;

    @Column
    private boolean finished;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "driver_pkey_fk"), name = "driver_id", referencedColumnName = "id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "member_pkey_fk"), name = "member_id", referencedColumnName = "id")
    private Member member;
}
