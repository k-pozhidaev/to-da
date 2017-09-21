package io.jedaev.dayon.controllers

import io.jedaev.dayon.dao.TasksRepository
import io.jedaev.dayon.model.tables.pojos.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping
class TaskController @Autowired constructor(private val tasksRepository:TasksRepository) {

    @GetMapping("/")
    fun list(): Flux<Task> {
        return Flux.fromStream(tasksRepository.findAll().stream())
    }

    @GetMapping("/{id}")
    fun one(@PathVariable("id") id: Long): Mono<Task>{

        return Mono.justOrEmpty(tasksRepository.findOne(id))

    }


}