package com.zeco.webfluxDemo.controller;

import com.zeco.webfluxDemo.dto.Customer;
import com.zeco.webfluxDemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {


    @Autowired
    private CustomerService service;


    //endpoint for non-reactive code
    @GetMapping("/")
    public List<Customer> getAllCustomers() {

        return service.loadAllCustomers();
    }


    //endpoint for reactive code... so here we basically replaced List with Flux, dont forget to add the produces parameter or else the code will not be reactive
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomersStream() {

        return service.loadAllCustomersStream();
    }

}