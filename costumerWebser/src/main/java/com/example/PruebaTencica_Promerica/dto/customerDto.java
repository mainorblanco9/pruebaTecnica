package com.example.PruebaTencica_Promerica.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Data
public class customerDto {
    private Long id ;
    private String id_cedula;
    private String firstName;
    private String lastName;
    private String phone;
    private String birthdate;

    // Constructor que inicializa todos los campos
    public customerDto(Long id, String id_cedula, String firstName, String lastName, String phone, String birthdate) {
        this.id = id;
        this.id_cedula = id_cedula;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.birthdate = birthdate;
    }

    // Constructor vacío
    public customerDto() {
    }
}
