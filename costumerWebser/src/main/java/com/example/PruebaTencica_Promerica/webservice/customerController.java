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

    @PostMapping("/crearcliente")
    public ResponseEntity<customerDto> createCustomer(@RequestBody customerDto customerDto) {
        customerDto createdCustomer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }
    @DeleteMapping("/eliminarcliente/{id_cedula}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id_cedula) {
        customerService.deleteCustomerById(id_cedula);
        return new ResponseEntity<>("Cliente eliminado exitosamente", HttpStatus.OK);
    }
    @PutMapping("/actualizar")
    public ResponseEntity<customerDto> updateCustomer(@RequestBody customerDto customerDto) throws customerNotFoundExe {
        customerDto updatedCustomer = customerService.updateCustomer(customerDto);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
    @GetMapping("/obtenerclienteporId/{idCedula}")
    public ResponseEntity<customerDto> getCustomerById(@PathVariable String idCedula) {
        try {
            customerDto customer = customerService.getCustomerById(idCedula);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (customerNotFoundExe e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ordenadosPorFechadeNacimientoDescendente")
    public ResponseEntity<List<customerDto>> getCustomersByBirthdateDesc() {
        List<customerDto> customers = customerService.getCustomersOrderByBirthdateDesc();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/OrdenadosPorId")
    public ResponseEntity<List<customerDto>> getCustomersByIdAsc() {
        List<customerDto> customers = customerService.getCustomersOrderById();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/OrdenadosPorNombreDeManeraAscendente")
    public ResponseEntity<List<customerDto>> getCustomersByFirstNameAsc() {
        List<customerDto> customers = customerService.getCustomersOrderByFirstNameAsc();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}
