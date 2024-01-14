package com.example.PruebaTencica_Promerica.service;

import com.example.PruebaTencica_Promerica.dto.customerDto;
import com.example.PruebaTencica_Promerica.exception.customerNotFoundExe;

import java.text.ParseException;
import java.util.List;

public interface customerService {


    // Método para crear un cliente
    public customerDto createCustomer(customerDto customerdto) throws ParseException;

    // Método para eliminar un cliente por ID
    public void deleteCustomerById(String id_cedula) throws customerNotFoundExe;

    // Método para actualizar un cliente
    public customerDto updateCustomer(customerDto customerDto) throws customerNotFoundExe, ParseException;

    // Método para obtener un cliente por ID
    public customerDto getCustomerById(String idCedula) throws customerNotFoundExe;

    // Método para listar clientes ordenados por fecha de nacimiento descendente
    public List<customerDto> getCustomersOrderByBirthdateDesc();

    // Método para listar clientes ordenados por ID
    public List<customerDto> getCustomersOrderById();

    // Método para listar clientes ordenados por nombre de manera ascendente
    public List<customerDto> getCustomersOrderByFirstNameAsc();

}
