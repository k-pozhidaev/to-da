package io.jedaev.dayon

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class DayOnApplication

fun main(args: Array<String>) {
    SpringApplication.run(DayOnApplication::class.java, *args)
}
