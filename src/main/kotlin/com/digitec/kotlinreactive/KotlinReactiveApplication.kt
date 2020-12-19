package com.digitec.kotlinreactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class KotlinReactiveApplication

fun main(args: Array<String>) {
    runApplication<KotlinReactiveApplication>(*args)
}
