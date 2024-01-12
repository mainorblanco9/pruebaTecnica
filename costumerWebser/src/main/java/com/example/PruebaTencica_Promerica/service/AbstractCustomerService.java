package com.example.PruebaTencica_Promerica.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.PruebaTencica_Promerica.dto.customerDto;
import com.example.PruebaTencica_Promerica.exception.customerNotFoundExe;
import com.example.PruebaTencica_Promerica.mapper.customerMapper;
import com.example.PruebaTencica_Promerica.model.customer;
import com.example.PruebaTencica_Promerica.repository.customerRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
    public class AbstractCustomerService implements customerService {

    private final com.example.PruebaTencica_Promerica.repository.customerRepository customerRepository;
    private final com.example.PruebaTencica_Promerica.mapper.customerMapper customerMapper;



    public AbstractCustomerService(customerRepository customerRepository, customerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }



    @Override
    public customerDto createCustomer(customerDto customerDto) {

        customer customerEntity = customerMapper.toEntity(customerDto);

        try {
            customerRepository.ingresarCliente(
                    customerEntity.getId_cedula(),
                    customerEntity.getFirstName(),
                    customerEntity.getLastName(),
                    customerEntity.getPhone(),
                    customerDto.getBirthdate()
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return customerDto;
    }

    public void deleteCustomerById(String id_cedula) {
        customerRepository.eliminarClientePorId(id_cedula);
    }

    @Override
    public customerDto updateCustomer(customerDto customerDto) throws customerNotFoundExe {
        try {

            List<Map<String, Object>> customersMapList = customerRepository.obtenerClientePorIdCedula(customerDto.getId_cedula());


            if (customersMapList.isEmpty()) {
                throw new customerNotFoundExe(String.format("No se encuentra el cliente con la cedula: %s No encontrado!", customerDto.getId_cedula()));
            }


            customer existingCustomer = mapToCustomerEntity(customersMapList.get(0));


            customerMapper.updateEntityFromDto(customerDto, existingCustomer);


            customerRepository.actualizarCliente(

                    existingCustomer.getId_cedula(),
                    existingCustomer.getFirstName(),
                    existingCustomer.getLastName(),
                    existingCustomer.getPhone(),
                    existingCustomer.getBirthdate()
            );


            return customerMapper.toDto(existingCustomer);

        } catch (DataAccessException e) {

            throw new RuntimeException("Error al actualizar el cliente: " + e.getMessage(), e);
        } catch (customerNotFoundExe e) {

            throw e;
        } catch (Exception e) {

            throw new RuntimeException("Error desconocido al actualizar el cliente: " + e.getMessage(), e);
        }
    }

    @Override
    public customerDto getCustomerById(customerDto customerDto) throws customerNotFoundExe {
        try {

            List<Map<String, Object>> customersMapList = customerRepository.obtenerClientePorIdCedula(customerDto.getId_cedula());


            if (!customersMapList.isEmpty()) {

                customer customerEntity = mapToCustomerEntity(customersMapList.get(0));

                // Convierte la entidad customer a un DT
                return customerMapper.toDto(customerEntity);
            }


            throw new customerNotFoundExe("Cliente no encontrado con la cedula: " + id_cedula);

        } catch (Exception e) {

            throw new RuntimeException("Error al obtener el cliente: " + e.getMessage(), e);
        }
    }
    private customer mapToCustomerEntity(Map<String, Object> customerMap) {

        customer customerEntity = new customer();

        customerEntity.setId_cedula((String) customerMap.get("id_cedula"));

        return customerEntity;
    }
    @Override
    public List<customerDto> getCustomersOrderByBirthdateDesc(customerDto customerDto) {
        List<customer> customers = customerRepository.obtenerClientesOrdenadosPorFechaNacimientoDesc(customerDto.getId_cedula());
        return customers.stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<customerDto> getCustomersOrderById(customerDto customerDto) {
        List<customer> customers = customerRepository.obtenerClientesOrdenadosPorId(customerDto.getId_cedula());
        return customers.stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<customerDto> getCustomersOrderByFirstNameAsc(customerDto customerDto) {
        List<customer> customers = customerRepository.obtenerClientesOrdenadosPorPrimerNombreAsc(customerDto.getId_cedula() );
        return customers.stream().map(customerMapper::toDto).collect(Collectors.toList());
    }
}
