package com.example.PruebaTencica_Promerica.mapper;

import com.example.PruebaTencica_Promerica.model.customer;
import com.example.PruebaTencica_Promerica.dto.customerDto;
import org.springframework.stereotype.Component;

@Component
public class customerMapper {

    public customerDto toDto(customer entity) {
        if (entity == null) {
            return null;
        }

        customerDto dto = new customerDto();
        dto.setId(entity.getId());
        dto.setId_cedula(String.valueOf(entity.getId_cedula()));
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        dto.setBirthdate(entity.getBirthdate());
        return dto;
    }

    public customer toEntity(customerDto dto) {
        if (dto == null) {
            return null;
        }

        customer entity = new customer();
        entity.setId(dto.getId());
        entity.setId_cedula(Integer.parseInt(dto.getId_cedula()));
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        entity.setBirthdate(dto.getBirthdate());
        return entity;
    }

}
