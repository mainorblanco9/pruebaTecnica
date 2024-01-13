package com.example.PruebaTencica_Promerica.webservice;

import com.example.PruebaTencica_Promerica.dto.customerDto;
import com.example.PruebaTencica_Promerica.exception.customerNotFoundExe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.PruebaTencica_Promerica.service.AbstractCustomerService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class customerController {
    private final AbstractCustomerService customerService;

    @Autowired
    public customerController(AbstractCustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<customerDto> createCustomer(@RequestBody customerDto customerDto) {
        customerDto createdCustomer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id_cedula}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id_cedula) {
        customerService.deleteCustomerById(id_cedula);
        return new ResponseEntity<>("Cliente eliminado exitosamente", HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<customerDto> updateCustomer(@RequestBody customerDto customerDto) throws customerNotFoundExe {
        customerDto updatedCustomer = customerService.updateCustomer(customerDto);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
    @GetMapping("/getById")
    public ResponseEntity<customerDto> getCustomerById(@RequestBody customerDto customerDto) throws customerNotFoundExe {
        customerDto customer = customerService.getCustomerById(customerDto);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/getByBirthdateDesc")
    public ResponseEntity<List<customerDto>> getCustomersByBirthdateDesc(@RequestBody customerDto customerDto) {
        List<customerDto> customers = customerService.getCustomersOrderByBirthdateDesc(customerDto);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/getByIdAsc")
    public ResponseEntity<List<customerDto>> getCustomersByIdAsc(@RequestBody customerDto customerDto) {
        List<customerDto> customers = customerService.getCustomersOrderById(customerDto);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/getByFirstNameAsc")
    public ResponseEntity<List<customerDto>> getCustomersByFirstNameAsc(@RequestBody customerDto customerDto) {
        List<customerDto> customers = customerService.getCustomersOrderByFirstNameAsc(customerDto);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}
