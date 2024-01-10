package com.example.PruebaTencica_Promerica.service;

import com.example.PruebaTencica_Promerica.dto.customerDto;
import com.example.PruebaTencica_Promerica.exception.customerNotFoundExe;
import com.example.PruebaTencica_Promerica.mapper.customerMapper;
import com.example.PruebaTencica_Promerica.model.customer;
import com.example.PruebaTencica_Promerica.repository.customerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

        customerRepository.ingresarCliente(customerEntity.getId_cedula(), customerEntity.getFirstName(), customerEntity.getLastName(), customerEntity.getPhone(), customerEntity.getBirthdate());
        return customerDto;
    }

    public void deleteCustomerById(String id_cedula) throws customerNotFoundExe {

        customerRepository.eliminarClientePorId(id_cedula);
    }

    @Override
    public customerDto updateCustomer(customerDto customerDto) {

        customer customerEntity = customerMapper.toEntity(customerDto);

        customerRepository.actualizarCliente(customerEntity.getId(), customerEntity.getId_cedula(), customerEntity.getFirstName(), customerEntity.getLastName(), customerEntity.getPhone(), customerEntity.getBirthdate());
        return customerDto;

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
