package io.poshidaev.toda.controller;

import io.poshidaev.toda.dto.GoalDTO;
import io.poshidaev.toda.entity.Goal;
import io.poshidaev.toda.repository.GoalRepository;
import io.poshidaev.toda.service.GoalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@RestController
@RequestMapping("/api/goal")
@Slf4j
public class GoalController {

    Scheduler jdbcScheduler;
    GoalRepository goalRepository;
    GoalService goalService;

    @Autowired
    public void setJdbcScheduler(Scheduler jdbcScheduler) {
        this.jdbcScheduler = jdbcScheduler;
    }

    @Autowired
    public void setGoalRepository(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Autowired
    public void setGoalService(GoalService goalService) {
        this.goalService = goalService;
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
    @Transactional
    Flux<GoalDTO> getAll(){
        return wrap( Flux.fromIterable(goalRepository.getAllWithTopics() ) ).map(GoalDTO::new);
    }

    @PostMapping
    Mono<GoalDTO> addOne(@RequestBody GoalDTO goal){
        return wrap( Mono.fromCallable( () -> goalService.addGoal(goal) ).map(GoalDTO::new) );
    }
}
