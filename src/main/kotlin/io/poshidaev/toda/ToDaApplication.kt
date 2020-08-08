package io.poshidaev.toda

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@EnableJpaRepositories
class ToDaApplication

fun main(args: Array<String>) {
	runApplication<ToDaApplication>(*args)
}
