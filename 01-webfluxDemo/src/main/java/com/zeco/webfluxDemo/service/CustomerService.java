package com.zeco.webfluxDemo.service;


import com.zeco.webfluxDemo.dao.CustomerDao;
import com.zeco.webfluxDemo.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao dao;


    //NON-REACTIVE
    public List<Customer> loadAllCustomers() {

        long start = System.currentTimeMillis();
        List<Customer> customers = dao.getCustomers();

        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return customers;
    }

    //REACTIVE
    //so here we basically replaced List with Flux
    public Flux<Customer> loadAllCustomersStream() {

        long start = System.currentTimeMillis();
        Flux<Customer> customers = dao.getCustomersStream();

        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return customers;
    }
}
