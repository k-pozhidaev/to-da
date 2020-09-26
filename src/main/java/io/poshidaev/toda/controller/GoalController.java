package io.poshidaev.toda.controller;

import io.poshidaev.toda.dto.GoalDTO;
import io.poshidaev.toda.repository.GoalApproachRepository;
import io.poshidaev.toda.repository.GoalRepository;
import io.poshidaev.toda.service.GoalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

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

    GoalApproachRepository goalApproachRepository;

    @Autowired
    public void setGoalApproachRepository(GoalApproachRepository goalApproachRepository) {
        this.goalApproachRepository = goalApproachRepository;
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

    @GetMapping
    Flux<GoalDTO> getAll(){
        return wrap( Flux.fromIterable(goalRepository.getAllWithTopics() ) ).map(GoalDTO::new);
    }

    @PostMapping
    Mono<GoalDTO> addOne(@RequestBody final GoalDTO goal){
        return wrap( Mono.fromCallable( () -> goalService.addGoal(goal) ).map(GoalDTO::new) );
    }

    @PatchMapping("/{id}/{date}")
    Mono<Integer> addApproach(@PathVariable final Long id, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable final LocalDate date){
        return wrap( Mono.fromCallable(() -> goalService.addApproach(id, date)));
    }

    @GetMapping("/a") //todo temp
    Mono<List<List<Long>>> getA(){
        return Mono.fromCallable(() -> goalApproachRepository.getCountByListIds(
                List.of(24L, 25L, 34L, 50L),
                Date.valueOf(LocalDate.of(2020, 9,26)))
        );
    }
}
