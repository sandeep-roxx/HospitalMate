package com.verma.sandeep.hospital.mate.bind;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponse {

    private Long id;
    private Date date;
    private int noOfSlots;
    private double amt;
    private String note;

}
