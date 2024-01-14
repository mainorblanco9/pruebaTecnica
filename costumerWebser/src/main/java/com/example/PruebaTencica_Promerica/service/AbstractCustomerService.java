package com.example.PruebaTencica_Promerica.service;

import com.example.PruebaTencica_Promerica.dto.customerDto;
import com.example.PruebaTencica_Promerica.exception.customerNotFoundExe;
import com.example.PruebaTencica_Promerica.mapper.customerMapper;
import com.example.PruebaTencica_Promerica.model.customer;
import com.example.PruebaTencica_Promerica.repository.customerRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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

            List<Map<String, Object>> customersMapList = customerRepository.obtenerClientePorIdCedula(customerDto.getId_cedula(),
                    customerDto.getFirstName(),
                    customerDto.getLastName(),
                    customerDto.getPhone(),
                    customerDto.getBirthdate());


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
    public customerDto getCustomerById(String idCedula) throws customerNotFoundExe {
        try {
            List<Map<String, Object>> customersMapList = customerRepository.obtenerClientePorIdCedula(idCedula, null, null, null, null);

            if (!customersMapList.isEmpty()) {
                customer customerEntity = mapToCustomerEntity(customersMapList.get(0));
                return customerMapper.toDto(customerEntity);
            }

            throw new customerNotFoundExe("Cliente no encontrado con la cedula: " + idCedula);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el cliente: " + e.getMessage(), e);
        }
    }
    private customer mapToCustomerEntity(Map<String, Object> customerMap) {

        customer customerEntity = new customer();

        customerEntity.setId_cedula((String) customerMap.get("p_id_cedula"));
        customerEntity.setFirstName((String) customerMap.get("out_firstname"));
        customerEntity.setLastName((String) customerMap.get("out_lastname"));
        customerEntity.setPhone((String) customerMap.get("out_phone"));
        customerEntity.setBirthdate((String) customerMap.get("out_birthdate"));

        return customerEntity;
    }
    @Override
    public List<customerDto> getCustomersOrderByBirthdateDesc() {
        try {
         List<Map<String, Object>> results = customerRepository.obtenerClientesOrdenadosPorFechaNacimientoDesc(
                    new String[1], new String[1], new String[1], new String[1], new String[1]
            );
            List<customerDto> customerDtos = new ArrayList<>();

            int size = ((String[]) results.get(0).get("out_id_cedulas")).length;

            for (int i = 0; i < size; i++) {
                customerDto dto = new customerDto();

                for (Map<String, Object> result : results) {
                    String[] idCedulas = (String[]) result.get("out_id_cedulas");
                    String[] firstNames = (String[]) result.get("out_firstNames");
                    String[] lastNames = (String[]) result.get("out_lastNames");
                    String[] phones = (String[]) result.get("out_phones");
                    String[] birthdates = (String[]) result.get("out_birthdates");

                    if (idCedulas != null && idCedulas.length > i) {
                        dto.setId_cedula(idCedulas[i]);
                    }
                    if (firstNames != null && firstNames.length > i) {
                        dto.setFirstName(firstNames[i]);
                    }
                    if (lastNames != null && lastNames.length > i) {
                        dto.setLastName(lastNames[i]);
                    }
                    if (phones != null && phones.length > i) {
                        dto.setPhone(phones[i]);
                    }
                    if (birthdates != null && birthdates.length > i) {
                        dto.setBirthdate(birthdates[i]);
                    }
                }

                customerDtos.add(dto);
            }

            return customerDtos;

        } catch (Exception e) {
            System.out.println("Error al obtener los clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    @Override
    public List<customerDto> getCustomersOrderById() {
        try {
            List<Map<String, Object>> results = customerRepository.obtenerClientesOrdenadosPorId(
                    new String[1], new String[1], new String[1], new String[1], new String[1]
            );
            List<customerDto> customerDtos = new ArrayList<>();

           int size = ((String[]) results.get(0).get("out_id_cedulas")).length;

            for (int i = 0; i < size; i++) {
                customerDto dto = new customerDto();

                for (Map<String, Object> result : results) {
                    String[] idCedulas = (String[]) result.get("out_id_cedulas");
                    String[] firstNames = (String[]) result.get("out_firstNames");
                    String[] lastNames = (String[]) result.get("out_lastNames");
                    String[] phones = (String[]) result.get("out_phones");
                    String[] birthdates = (String[]) result.get("out_birthdates");

                    if (idCedulas != null && idCedulas.length > i) {
                        dto.setId_cedula(idCedulas[i]);
                    }
                    if (firstNames != null && firstNames.length > i) {
                        dto.setFirstName(firstNames[i]);
                    }
                    if (lastNames != null && lastNames.length > i) {
                        dto.setLastName(lastNames[i]);
                    }
                    if (phones != null && phones.length > i) {
                        dto.setPhone(phones[i]);
                    }
                    if (birthdates != null && birthdates.length > i) {
                        dto.setBirthdate(birthdates[i]);
                    }
                }

                customerDtos.add(dto);
            }

            return customerDtos;

        } catch (Exception e) {
            System.out.println("Error al obtener los clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<customerDto> getCustomersOrderByFirstNameAsc() {
        try {
            List<Map<String, Object>> results = customerRepository.obtenerClientesOrdenadosPorPrimerNombreAsc(
                    new String[1], new String[1], new String[1], new String[1], new String[1]
            );
            List<customerDto> customerDtos = new ArrayList<>();

            int size = ((String[]) results.get(0).get("out_id_cedulas")).length;

            for (int i = 0; i < size; i++) {
                customerDto dto = new customerDto();

                for (Map<String, Object> result : results) {
                    String[] idCedulas = (String[]) result.get("out_id_cedulas");
                    String[] firstNames = (String[]) result.get("out_firstNames");
                    String[] lastNames = (String[]) result.get("out_lastNames");
                    String[] phones = (String[]) result.get("out_phones");
                    String[] birthdates = (String[]) result.get("out_birthdates");

                    if (idCedulas != null && idCedulas.length > i) {
                        dto.setId_cedula(idCedulas[i]);
                    }
                    if (firstNames != null && firstNames.length > i) {
                        dto.setFirstName(firstNames[i]);
                    }
                    if (lastNames != null && lastNames.length > i) {
                        dto.setLastName(lastNames[i]);
                    }
                    if (phones != null && phones.length > i) {
                        dto.setPhone(phones[i]);
                    }
                    if (birthdates != null && birthdates.length > i) {
                        dto.setBirthdate(birthdates[i]);
                    }
                }

                customerDtos.add(dto);
            }

            return customerDtos;

        } catch (Exception e) {
            System.out.println("Error al obtener los clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
