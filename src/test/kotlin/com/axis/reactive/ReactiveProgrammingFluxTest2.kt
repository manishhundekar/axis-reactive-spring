package com.axis.reactive

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import java.time.Duration


class ReactiveProgrammingFluxTest2 {

	@Test
	fun fluxTest() {
		val flux = Flux.just("A","B","C")
						.log()

		StepVerifier.create(flux)
			.expectNext("A","B","D")
			//.expectNext("A")
			//.expectNext("B")
			//.expectNext("C")
			.verifyComplete()
	}

	@Test
	fun fluxTestWithError() {
		val flux = Flux.just("A","B","C")
			.concatWith(Flux.error(RuntimeException("An Error has occurred")))
			.log()

		StepVerifier.create(flux)
			.expectNext("A","B","C")
			.expectError(java.lang.RuntimeException::class.java)
			.verify()

	}
}