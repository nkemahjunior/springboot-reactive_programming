package com.zeco.webfluxDemo.handler;


import com.zeco.webfluxDemo.dao.CustomerDao;
import com.zeco.webfluxDemo.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao dao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request){

        Flux<Customer>  customerList = dao.getCustomersList();
        return ServerResponse.ok().body(customerList, Customer.class);
    }


    //acessing path variables
    public Mono<ServerResponse> findCustomer(ServerRequest request){

        int customerID = Integer.valueOf( request.pathVariable("input"));
        //dao.getCustomerList().filter( c -> c.getId() == customerID).take(1).single();

        Mono<Customer> customerMono = dao.getCustomersList().filter( c -> c.getId() == customerID ).next();
        return ServerResponse.ok().body(customerMono,Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request){

        //extract the incoming customer object and convert to mono( since we will pass just one json object at postman)
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);

        Mono<String> saveResponse = customerMono.map( el -> el.getId() + " : " + el.getName());

        return  ServerResponse.ok().body(saveResponse, String.class);
    }
}
