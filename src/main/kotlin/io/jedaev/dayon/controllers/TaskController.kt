package io.jedaev.dayon.controllers

import io.jedaev.dayon.model.Tables.*
import io.jedaev.dayon.model.tables.pojos.Task
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping
class TaskController @Autowired constructor(private val dsl: DSLContext) {

    @GetMapping("/")
    fun list(): Flux<Task> {
        return Flux.fromStream(
                dsl.selectFrom(TASK)
                        .fetch{ t-> Task(t.id, t.text) }
                        .stream()
        )
    }

    @GetMapping("/{id}")
    fun one(@PathVariable("id") id: Long): Mono<Task>{

        return Mono.justOrEmpty(
                dsl
            .selectFrom(TASK)
            .where(TASK.ID.eq(id))
            .fetchOne{t -> Task(t.id, t.text) }
        )

    }


}