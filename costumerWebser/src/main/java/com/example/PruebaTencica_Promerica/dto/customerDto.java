package com.example.PruebaTencica_Promerica.dto;



import lombok.Data;

import java.util.Date;
@Data
public class customerDto {
    private Long id ;
    private String id_cedula;
    private String firstName;
    private String lastName;
    private String phone;
    private Date birthdate;

    public customerDto(int idCedula, String firstName, String lastName, String phone, Date birthdate) {
    }

    public customerDto() {

    }
}
