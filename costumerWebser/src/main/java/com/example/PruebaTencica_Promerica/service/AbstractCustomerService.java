package com.example.PruebaTencica_Promerica.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.PruebaTencica_Promerica.dto.customerDto;
import com.example.PruebaTencica_Promerica.exception.customerNotFoundExe;
import com.example.PruebaTencica_Promerica.mapper.customerMapper;
import com.example.PruebaTencica_Promerica.model.customer;
import com.example.PruebaTencica_Promerica.repository.customerRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

        List<customer> customers = new ArrayList<>();
        try {
            customers = customerRepository.obtenerClientePorIdCedula(customerDto.getId_cedula());
}catch (Exception e){
 System.out.println(e.getMessage());
}

        if (customers.isEmpty()) {
            throw new customerNotFoundExe(String.format("No se encuentra el cliente con la cedula: %s No encontrado!", customerDto.getId_cedula()));
        }


        customer existingCustomer = customers.get(0);


        customerMapper.updateEntityFromDto(customerDto, existingCustomer);


        customerRepository.actualizarCliente(
                existingCustomer.getId(),
                existingCustomer.getId_cedula(),
                existingCustomer.getFirstName(),
                existingCustomer.getLastName(),
                existingCustomer.getPhone(),
                String.valueOf(existingCustomer.getBirthdate())
        );


        return customerMapper.toDto(existingCustomer);
    }
    @Override
    public customerDto getCustomerById(String id_cedula)throws customerNotFoundExe {

        List<customer> customers = customerRepository.obtenerClientePorIdCedula(id_cedula);
        if (!customers.isEmpty()) {
            return customerMapper.toDto(customers.get(0));
        }
        throw new customerNotFoundExe("Cliente no encontrado");
    }

    @Override
    public List<customerDto> getCustomersOrderByBirthdateDesc() {
        List<customer> customers = customerRepository.obtenerClientesOrdenadosPorFechaNacimientoDesc();
        return customers.stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<customerDto> getCustomersOrderById() {
        List<customer> customers = customerRepository.obtenerClientesOrdenadosPorId();
        return customers.stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<customerDto> getCustomersOrderByFirstNameAsc() {
        List<customer> customers = customerRepository.obtenerClientesOrdenadosPorPrimerNombreAsc();
        return customers.stream().map(customerMapper::toDto).collect(Collectors.toList());
    }
}
