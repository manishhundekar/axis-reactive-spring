package com.axis.reactive

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.lang.RuntimeException
import java.time.Duration


class ReactiveProgrammingMonoTest {

	@Test
	fun monoTest() {
		Mono.just("A")
			.log() //Mono<String>
			.subscribe { item -> println(item) }
	}

	@Test
	fun monoTest2() {
		Mono.just("A")
			.concatWith(Mono.error(RuntimeException("An Error has occurred")))
			.log()
			.subscribe { item -> println(item) }
	}

	@Test
	fun junitTest() {
		val mono = Mono.just("A")
			.log()

		StepVerifier.create(mono)
			.expectNext("A")
			.verifyComplete()
	}
}
