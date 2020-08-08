package io.poshidaev.toda.controller

import io.poshidaev.toda.dto.TaskDTO
import io.poshidaev.toda.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler

@RestController
@RequestMapping(value = ["/task"])
class TaskController @Autowired constructor(
        private var taskRepository: TaskRepository,
        @Qualifier("jdbcScheduler") private val jdbcScheduler: Scheduler
) {

    @GetMapping
    fun getAll() = Flux.defer {
        Flux.fromIterable(taskRepository.findAll())
    }.subscribeOn(jdbcScheduler)

    @PostMapping
    fun addOne(@RequestBody task: TaskDTO) = Mono.fromCallable {
        taskRepository.save(task.toTask())
    }.subscribeOn(jdbcScheduler)
}