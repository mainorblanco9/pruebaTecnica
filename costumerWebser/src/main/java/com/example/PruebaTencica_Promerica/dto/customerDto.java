package com.example.PruebaTencica_Promerica.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class customerDto {
    private Long id ;
    private String id_cedula;
    private String firstName;
    private String lastName;
    private String phone;
    private String birthdate;

    public customerDto() {

    }
}
