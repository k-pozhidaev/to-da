package io.poshidaev.toda

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ToDaApplication

fun main(args: Array<String>) {
	runApplication<ToDaApplication>(*args)
}
