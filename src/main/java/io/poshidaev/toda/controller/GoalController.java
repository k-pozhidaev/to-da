package io.poshidaev.toda.controller;

import io.poshidaev.toda.dto.GoalDTO;
import io.poshidaev.toda.entity.Goal;
import io.poshidaev.toda.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@RestController
@RequestMapping("/api/goal")
public class GoalController {

    Scheduler jdbcScheduler;
    GoalRepository goalRepository;

    @Autowired
    public void setJdbcScheduler(Scheduler jdbcScheduler) {
        this.jdbcScheduler = jdbcScheduler;
    }

    @Autowired
    public void setGoalRepository(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    <T> Mono<T> wrap (Mono<T> publisher) {
        return Mono.defer(() -> publisher ).subscribeOn(jdbcScheduler);
    }
    <T> Flux<T> wrap (Flux<T> publisher) {
        return Flux.defer(() -> publisher ).subscribeOn(jdbcScheduler);
    }

    @GetMapping("/{id}")
    Mono<Goal> getOne(@PathVariable Long id){
        return wrap( Mono.fromCallable( () -> goalRepository.getOne(id) )  );
    }

    @GetMapping
    Flux<Goal> getAll(){
        return wrap( Flux.fromIterable(goalRepository.findAll() ) );
    }

    @PostMapping
    Mono<Goal> addOne(@RequestBody GoalDTO goal){
        return wrap( Mono.fromCallable( () -> goalRepository.save(goal.toEntity()) ) );
    }
}
