package com.zeco.webfluxDemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {


    @Test
    public void testMono(){
        Mono<String> monoString = Mono.just("abc");
        monoString.subscribe(System.out::println);
    }

    @Test
    public void testFlux(){
        Flux<String> fluxString = Flux.just("data1","data2","data3","data n");
        fluxString.subscribe(System.out::println);
    }
}
