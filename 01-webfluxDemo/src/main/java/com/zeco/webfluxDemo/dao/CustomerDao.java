package com.zeco.webfluxDemo.dao;

import com.zeco.webfluxDemo.dto.Customer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void sleepExecution(int i){
        try {
            Thread.sleep(1000);//1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // NON REACTIVE : BLOCKING
    public List<Customer> getCustomers(){
        //creating 50 customer objects
        return IntStream.rangeClosed(1,30)
                .peek(CustomerDao::sleepExecution)
                .peek(i -> System.out.println("processing count : " + i))
                .mapToObj( i -> new Customer(i,"customer "+i) )
                .collect(Collectors.toList());
    }



    //SAME CODE ABOVE BUT NOW IN THE REACTIVE WAY : NON-BLOCKING
    //so here we basically replaced List with Flux
    public Flux<Customer> getCustomersStream(){
        return Flux.range(1,30)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing count REACTIVE WAY:  " + i))
                .map(i -> new Customer(i,"customer" + i));
    }

    public Flux<Customer> getCustomersList(){

        return Flux.range(1,50)
                .doOnNext(i -> System.out.println("processing count REACTIVE WAY:  " + i))
                .map(i -> new Customer(i,"customer" + i));
    }






}
