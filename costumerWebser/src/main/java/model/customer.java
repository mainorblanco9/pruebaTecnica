package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 50)
    private Long id;

    @Column(length = 50)
    private int id_cedula;

    @Column(length = 20)
    private String firstName;

    @Column(length = 20)
    private String lastName;

    @Column(length = 20)
    private String phone;


    @Temporal(TemporalType.DATE)
    @Column(length = 20)
    private Date birthdate;


}
