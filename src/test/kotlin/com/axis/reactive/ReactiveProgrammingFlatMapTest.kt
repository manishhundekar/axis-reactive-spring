package com.axis.reactive

import org.junit.jupiter.api.Test
import org.springframework.boot.context.properties.bind.Bindable.listOf
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import reactor.core.scheduler.Schedulers.parallel
import reactor.kotlin.core.publisher.toMono
import reactor.test.StepVerifier

class ReactiveProgrammingFlatMapTest {
    @Test
    fun flatMapTest() {
        val employeeIds = listOf("1", "2", "3", "4", "5", "6", "7", "8")

        val employeeName = Flux.fromIterable(employeeIds)
                .flatMap { id -> getEmployeeDetails(id) }
                .log()

        StepVerifier.create(employeeName)
                .expectNextCount(8)
                .verifyComplete()
    }

    @Test
    fun flatMapTestUsingParallelScheduler() {
        val employeeIds = listOf("1", "2", "3", "4", "5", "6", "7", "8")

        val employeeName = Flux.fromIterable(employeeIds)
                .window(2)
                .flatMap {
                    identifiers -> identifiers.flatMap {
                    id -> getEmployeeDetails(id)
                }.subscribeOn(parallel()) }
                .log()

        StepVerifier.create(employeeName)
                .expectNextCount(8)
                .verifyComplete()
    }

    private fun getEmployeeDetails(id: String?): Mono<String> {
        val employees = mapOf(
                "1" to "Sam",
                "2" to "Ajay",
                "3" to "Ram",
                "4" to "Sameer",
                "5" to "Jay",
                "6" to "Ramesh",
                "7" to "Sami",
                "8" to "Arun"
        )

        Thread.sleep(1000)
        return employees.getOrDefault(id, "Not found").toMono()
    }
}