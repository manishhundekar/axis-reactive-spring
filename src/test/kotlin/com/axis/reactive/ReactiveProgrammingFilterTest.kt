package com.axis.reactive

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class ReactiveProgrammingFilterTest {
    @Test
    fun filterTest() {
        val cities = listOf("Kolkata", "Pune", "Mumbai", "Jaipur", "Delhi")

        val fluxCities = Flux.fromIterable(cities)
                             .filter { city -> city.length > 6 }
                             .log()

        StepVerifier.create(fluxCities)
                .expectNext("Kolkata")
                .verifyComplete()
    }

    @Test
    fun filterTest2() {
        val cities = listOf("Kolkata", "Pune", "Mumbai", "Jaipur", "Delhi")

        val fluxCities = Flux.fromIterable(cities)
            .filter { city -> city.startsWith("D")}
            .log()

        StepVerifier.create(fluxCities)
            .expectNext("Delhi")
            .verifyComplete()
    }
}