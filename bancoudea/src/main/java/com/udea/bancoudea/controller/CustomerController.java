package com.udea.bancoudea.controller;

import com.udea.bancoudea.DTO.CustomerDTO;
import com.udea.bancoudea.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerFacade;

    public CustomerController(CustomerService customerFacade) {
        this.customerFacade = customerFacade;
    }

    //Obtener todos los cliente
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerFacade.getAllCustomer());
    }

    //Obtener un cliente por un ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerFacade.getCustomerById(id));
    }

   //Obtener un cliente por un cliente por cuenta
    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<CustomerDTO> getCustomerByAccountNumber(@PathVariable String accountNumber) {
        return ResponseEntity.ok(customerFacade.getCustomerByAccountNumber(accountNumber));
    }

//Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        if(customerDTO.getBalance() == null) {
            throw new IllegalArgumentException("Balance cannot be null");
        }
        return ResponseEntity.ok(customerFacade.createCustomer(customerDTO));
    }
}
