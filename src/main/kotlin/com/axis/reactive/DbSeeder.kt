package com.axis.reactive

import com.axis.reactive.model.Employee
import com.axis.reactive.repository.EmployeesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Component
class DbSeeder(
        @Autowired
        val employeesRepository: EmployeesRepository,

        @Autowired
        val reactiveMongoOperations: ReactiveMongoOperations
) : CommandLineRunner{

    val employeesList = Flux.just(
            Employee(null, "Rajkumar", "CS"),
            Employee(null, "Raman", "CS"),
            Employee(null, "Ajay", "Mechanical"),
            Employee(null, "Kumar", "Civil"))

    override fun run(vararg args: String?) {
        dbSetup()
    }

    private fun dbSetup() {
        val employees = employeesList.flatMap {
            employeesRepository.save(it)
        }

        reactiveMongoOperations.collectionExists(Employee::class.java)
                .flatMap {
                    when(it){
                        true -> reactiveMongoOperations.dropCollection(Employee::class.java)
                        false -> Mono.empty()
                    }
                }
                .thenMany(reactiveMongoOperations.createCollection(Employee::class.java))
                .thenMany(employees)
                .thenMany(employeesRepository.findAll())
                .subscribe({ println(it)}, {error -> println( error)}, { println(" -- Database has been initialized")
                })
    }
}