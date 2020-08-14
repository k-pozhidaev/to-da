package io.poshidaev.toda.controller

import io.poshidaev.toda.dto.TaskDTO
import io.poshidaev.toda.entity.Task
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
    fun <T> wrap (publisher : Mono<T>) : Mono<T> = Mono.defer { publisher }.subscribeOn(jdbcScheduler)
    fun <T> wrap (publisher : Flux<T>) : Flux<T> = Flux.defer { publisher }.subscribeOn(jdbcScheduler)

    @GetMapping(value = ["/{id}"])
    fun getOne(@PathVariable id: Long):Mono<Task> = wrap( Mono.fromCallable { taskRepository.getOne(id) } )

    @GetMapping
    fun getAll():Flux<Task> = wrap(Flux.fromIterable(taskRepository.findAll()))

    @PostMapping
    fun addOne(@RequestBody task: TaskDTO) = wrap(Mono.fromCallable { taskRepository.save(task.toTask()) })
}