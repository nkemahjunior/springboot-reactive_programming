package com.zeco.webfluxDemo.router;


import com.zeco.webfluxDemo.handler.CustomerHandler;
import com.zeco.webfluxDemo.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;

    @Autowired
    private CustomerStreamHandler handler2;


    @Bean
    public RouterFunction<ServerResponse> routerFunction(){

        return RouterFunctions.route()
                .GET("/router/customer",handler::loadCustomers)
                .GET("/router/customer/stream",handler2::getCustomers)
                .GET("/router/customer/{input}",handler::findCustomer)
                .POST("/router/customer/save",handler::saveCustomer)
                .build();
    }
}
