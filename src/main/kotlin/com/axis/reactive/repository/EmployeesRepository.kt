package com.axis.reactive.repository

import com.axis.reactive.model.Employee
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

interface EmployeesRepository : ReactiveMongoRepository<Employee, String>