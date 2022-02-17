package com.axis.reactive.controller

import com.axis.reactive.model.Employee
import com.axis.reactive.repository.EmployeesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("v1")
class EmployeeController(
        @Autowired
        val employeesRepository: EmployeesRepository
) {

    @GetMapping("/employees")
    fun getAllEmployees(): Flux<Employee> {
        return employeesRepository.findAll()
    }

    @GetMapping("{id}")
    fun getEmployeeById(@PathVariable id: String): Mono<Employee> {
        return employeesRepository.findById(id)
    }

    @PostMapping("/employees")
    fun save(@RequestBody employee: Employee): Mono<Employee> {
        return employeesRepository.save(employee)
    }

    @PutMapping("/update")
    fun update(@RequestBody employee: Employee): Mono<Employee> {
        return employeesRepository.save(employee)
    }

    @DeleteMapping
    fun delete(): Mono<Void> {
        return employeesRepository.deleteAll()
    }

    @DeleteMapping("/employees/{id}")
    fun delete(@PathVariable id: String): Mono<Void> {
        return employeesRepository.deleteById(id)
    }
}