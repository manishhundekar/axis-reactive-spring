package com.axis.reactive

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import java.time.Duration


class ReactiveProgrammingFluxTest {

	@Test
	fun fluxTest() {
		Flux.just("A","B","C")
			.log()
			//.subscribe { item -> println(item) }
			//.subscribe { println(it) }
			.subscribe()
	}

	@Test
	fun fluxTestWithError() {
		Flux.just("A","B","C")
			.concatWith(Flux.error(RuntimeException("An Error has occurred")))
			.log()
			.subscribe { item -> println(item) }
	}

	@Test
	fun fluxTestWithError2() {
		Flux.just("A","B","C")
			.concatWith(Flux.error(RuntimeException("An Error has occurred")))
			.log()
			.subscribe ({ item -> println(item) },
						{ error -> println("ERROR CONSUMER : $error") },
						{ println("Completed") })
	}

	@Test
	fun fluxTestWithError3() {
		Flux.just("A","B","C")
			.concatWith(Flux.error(RuntimeException("An Error has occurred")))
			.onErrorReturn("Call from onErrorReturn")
			.log()
			.subscribe { item -> println(item) }
	}

	@Test
	fun fluxWithList() {
		val list = listOf("A","B","C")
		Flux.just(list)
			.log()
			.subscribe { item -> println(item) }
	}


	@Test
	fun fluxWithIterable2() {
		val list = listOf("A","B","C")
		Flux.fromIterable(list)
			.log()
			.subscribe { item -> println(item) }
	}

	@Test
	fun fluxWithRange() {
		Flux.range(5,6)
			.log()
			.subscribe { item -> println(item) }
	}

	@Test
	fun fluxFromInterval() {
		Flux.interval(Duration.ofSeconds(1))
			.log()
			.subscribe { item -> println(item) }
		Thread.sleep(10000)
	}

	@Test
	fun fluxWithTakeOperator() {
		Flux.range(5,6)
			.log()
			.take(3)
			.subscribe { item -> println(item) }
	}
}