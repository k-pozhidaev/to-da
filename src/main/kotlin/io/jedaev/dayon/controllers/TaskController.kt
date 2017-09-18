package io.jedaev.dayon.controllers

import io.jedaev.dayon.model.Tables
import org.jooq.DSLContext
import org.jooq.Record
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping
class TaskController @Autowired constructor(private val dsl: DSLContext) {

    @GetMapping("/")
    fun demo(): Flux<Record>{
        return Flux.fromStream(dsl.select().from(Tables.TASK)
                .fetch().stream())
    }
}