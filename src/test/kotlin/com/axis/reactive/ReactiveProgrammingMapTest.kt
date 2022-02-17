package com.axis.reactive

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux

class ReactiveProgrammingMapTest {
    @Test
    fun mapTest() {
        Flux.range(1, 5)
                .map { "$it String form" }
                .subscribe { println(it) }
    }

    @Test
    fun mapTest2() {
        Flux.range(1, 10)
                .map { it * it }
                .subscribe { data -> println(data) }
    }

    @Test
    fun mapTest3() {
        Flux.range(1, 10)
            .map { data -> data }
            .filter {data -> data % 2 == 0}
            .subscribe { data -> println(data) }
    }
}