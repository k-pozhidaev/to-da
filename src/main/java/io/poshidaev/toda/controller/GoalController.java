package io.poshidaev.toda.controller;

import io.poshidaev.toda.dto.GoalDTO;
import io.poshidaev.toda.repository.GoalRepository;
import io.poshidaev.toda.service.GoalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.time.LocalDate;
import java.util.Optional;

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


    <T> Mono<T> wrap (final Mono<T> publisher) {
        return Mono.defer(() -> publisher ).subscribeOn(jdbcScheduler);
    }
    <T> Flux<T> wrap (final Flux<T> publisher) {
        return Flux.defer(() -> publisher ).subscribeOn(jdbcScheduler);
    }

    @GetMapping("/{id}")
    Mono<GoalDTO> getOne(@PathVariable final Long id){
        return wrap( Mono.fromCallable( () -> goalService.getOne(id) ) );
    }

    @GetMapping(value = {"/date", "/date/{date}"})
    Flux<GoalDTO> getAll(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") final Optional<LocalDate> date){
        return wrap( Flux.fromIterable(
                goalService.getAllWithTopicsAndApproachesCount(
                    date.orElse(LocalDate.now())
                )
        ) );
    }

    @PostMapping
    Mono<GoalDTO> addOne(@RequestBody final GoalDTO goal){
        return wrap( Mono.fromCallable( () -> goalService.addGoal(goal) ).map(GoalDTO::new) );
    }

    @PatchMapping("/{id}/{date}")
    Mono<Integer> addApproach(
            @PathVariable final Long id,
            @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable final LocalDate date
    ) {
        return wrap( Mono.fromCallable(() -> goalService.addApproach(id, date)));
    }

}
